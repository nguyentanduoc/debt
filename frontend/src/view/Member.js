import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Button, Card, Col, Form, Icon, Input, Row, Select, Table, Checkbox, Tag, Popconfirm, Spin} from 'antd';
import moment from 'moment'
import {createUser, getAgency, getAllMember, deleteMember} from "../service/MemberService";

class Member extends Component {

  constructor(props) {
    super(props);
    this.state = {
      loading: false,
      agencies: [],
      columns: [
        {
          title: 'Tên thanh viên',
          dataIndex: 'fullName',
          key: 'fullName',
        },
        {
          title: 'Địa chỉ',
          dataIndex: 'address',
          key: 'address',
        },
        {
          title: 'Số điện thoại',
          dataIndex: 'phoneNumber',
          key: 'phoneNumber',
        },
        {
          title: 'Là nhân viên',
          dataIndex: 'agency',
          key: 'agency',
          render: text => (
            <Tag color={text ? 'geekblue' : 'green'} key={text}>
              {text ? 'Nhân Viên' : 'Đại lý'}
            </Tag>),
        },
        {
          title: 'Ngày tạo',
          dataIndex: 'createdDate',
          key: 'createdDate',
          render: text => (<div>{moment(text).format("DD-MM-YYYY")}</div>),
        },
        {
          title: 'Tuỳ chọn',
          dataIndex: 'action',
          key: 'action',
          render: (text, record) =>
            this.state.members.length >= 1 ? (
              <Popconfirm title="Sure to delete?" onConfirm={() => this.handleDelete(record.id)}>
                <a>Delete</a>
              </Popconfirm>
            ) : null,
        },
      ],
      members: null
    };
  }

  async handleDelete(idMember) {
    await this.setState({loading: true});
    await deleteMember(idMember);
    await this.loadMember();
    await this.loadAgency();
    await this.setState({loading: false});
  }

  async loadAgency() {
    const agencies = await getAgency();
    this.setState({agencies: agencies.data})
  }

  UNSAFE_componentWillMount() {
    this.loadAgency()
    this.loadMember()
  }

  createMember = (e) => {
    e.preventDefault();
    this.props.form.validateFields(async (err, values) => {
      if (!err) {
        await this.setState({loading: true});
        try {
          await createUser(values);
          await this.loadMember();
          await this.loadAgency();
          this.props.form.resetFields();
        } catch (e) {
          console.log(e)
        }
        await this.setState({loading: false});
      }
    });
  };

  loadMember = async () => {
    try {
      const members = await getAllMember();
      this.setState({members: members.data});
    } catch (e) {
      console.log(e)
    }
  };

  render() {
    const {getFieldDecorator} = this.props.form;
    return (
      <div style={{margin: 10}}>
        <Spin spinning={this.state.loading}>
          <Row gutter={16}>
            <Col span={6}>
              <Card>
                <Form onSubmit={this.handleSubmit}>
                  <Form.Item>
                    {getFieldDecorator('fullName', {
                      rules: [{required: true, message: 'Nhập tên Thành Viên'}],
                    })(
                      <Input
                        prefix={<Icon type="user" style={{color: 'rgba(0,0,0,.25)'}}/>}
                        placeholder="Nhập tên Thành Viên"
                      />
                    )}
                  </Form.Item>
                  <Form.Item>
                    {getFieldDecorator('agency', {
                      valuePropName: 'checked',
                      initialValue: false,
                    })(
                      <Checkbox>Là nhân viên</Checkbox>
                    )}
                  </Form.Item>
                  <Form.Item>
                    {getFieldDecorator('memberOf')(
                      <Select
                        showSearch
                        placeholder="Thuộc Thành viên"
                      >
                        <Select.Option key={0} value={null}>Null</Select.Option>
                        {this.state.agencies && this.state.agencies.length > 0 && this.state.agencies.map(member => (
                          <Select.Option key={member.id} value={member.id}>{member.fullName}</Select.Option>
                        ))}
                      </Select>
                    )}
                  </Form.Item>
                  <Form.Item>
                    {getFieldDecorator('address')(
                      <Input
                        type="address"
                        placeholder="Địa chỉ"
                      />
                    )}
                  </Form.Item>
                  <Form.Item>
                    {getFieldDecorator('phoneNumber')(<Input placeholder="Số điện thoại"/>)}
                  </Form.Item>
                  <Form.Item>
                    <Button type="primary" onClick={this.createMember}>
                      Tạo
                    </Button>
                  </Form.Item>
                </Form>
              </Card>
            </Col>
            <Col span={18}>
              <Table columns={this.state.columns} dataSource={this.state.members} rowKey={'id'} bordered={true}/>
            </Col>
          </Row>
        </Spin>
      </div>
    );
  }
}

function mapStateToProps(state) {
  return {};
}


export default connect(
  mapStateToProps,
)(Form.create()(Member));
