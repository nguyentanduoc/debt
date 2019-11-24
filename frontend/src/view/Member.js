import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Button, Card, Col, Form, Icon, Input, Row, Select, Table, Checkbox, Tag, Popconfirm} from 'antd';
import moment from 'moment'
import {createUser, getAgency, getAllMember, deleteMember} from "../service/MemberService";

class Member extends Component {

  constructor(props) {
    super(props);
    this.state = {
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
          title: 'Là đại lý',
          dataIndex: 'agency',
          key: 'agency',
          render: text => (
            <Tag color={text ? 'geekblue' : 'green'} key={text}>
              {text ? 'Đại lý' : 'Thành viên'}
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

  handleDelete(idMember) {
    deleteMember(idMember).then(() => {
      this.loadMember()
    })
  }

  UNSAFE_componentWillMount() {
    getAgency()
      .then(response => {
        this.setState({agencies: response.data})
      })
      .catch(error => {

      });
    this.loadMember()
  }

  createMember = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        createUser(values)
          .then(() => {
            this.loadMember()
          })
      }
    });
  };

  loadMember = () => {
    getAllMember()
      .then((response) => {
        this.setState({members: response.data})
      })
      .catch(err => {
        console.log(err)
      })
  };

  render() {
    const {getFieldDecorator} = this.props.form;
    return (
      <div style={{margin: 10}}>
        <Row gutter={16}>
          <Col span={8}>
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
                    initialValue: false,})(
                    <Checkbox>Là đại lý</Checkbox>
                  )}
                </Form.Item>
                <Form.Item>
                  {getFieldDecorator('memberOf')(
                    <Select
                      showSearch
                      placeholder="Thuộc Thành viên"
                    >
                      <Select.Option key={0} value={null}>Null</Select.Option>
                      {this.state.agencies && this.state.agencies.map(member => (
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
                  {getFieldDecorator('phone')(<Input placeholder="Số điện thoại"/>)}
                </Form.Item>
                <Form.Item>
                  <Button type="primary" onClick={this.createMember}>
                    Tạo
                  </Button>
                </Form.Item>
              </Form>
            </Card>
          </Col>
          <Col span={16}>
            <Table columns={this.state.columns} dataSource={this.state.members} rowKey={'id'} bordered={true}/>
          </Col>
        </Row>
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