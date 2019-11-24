import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Button, Card, Col, DatePicker, Divider, Form, Row, Select, Table } from "antd";
import { getAgency, statisticalSearch } from "../service/MemberService";
import ReactToPrint from 'react-to-print';

const Option = Select.Option;
const { RangePicker } = DatePicker;
const dateFormat = 'DD-MM-YYYY';

class Statistical extends Component {

  state = {
    agencies: [],
    dataSource: null,
    totalDebt: 0,
    totalHistory: 0,
    totalRest: 0,
    columns: [
      {
        title: "Khách Hàng",
        dataIndex: 'fullName',
        key: 'fullName'
      },
      {
        title: "Doanh thu",
        dataIndex: 'sumDebt',
        key: 'sumDebt'
      },
      {
        title: "Tổng thu",
        dataIndex: 'sumHistory',
        key: 'sumHistory'
      }
    ]
  };

  UNSAFE_componentWillMount() {
    getAgency()
      .then(response => {
        this.setState({ agencies: response.data })
      })
      .catch(error => {

      })
  }

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields(async (err, values) => {
      statisticalSearch(values)
        .then((response) => {
          this.setState({
            dataSource: response.data.member,
            totalHistory: response.data.totalHistory,
            totalDebt: response.data.totalDebt,
            totalRest: response.data.totalRest
          })
        })
        .catch((err) => {
          console.log(err);
        })
    });
  };

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <div>
        <Row style={{ margin: 10 }}>
          <Col span={23}>
            <Form layout="inline" onSubmit={this.handleSubmit}>
              <Form.Item>
                {getFieldDecorator('agencyId', {})(
                  <Select
                    showSearch
                    style={{ width: 200 }}
                    placeholder="Chọn đại lý"
                    optionFilterProp="children"
                    onChange={this.onChangeAgency}
                    filterOption={(input, option) =>
                      option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                    }>
                    {
                      this.state.agencies.map(member => (
                        <Option key={member.id} value={member.id}>{member.fullName}</Option>
                      ))
                    }
                  </Select>
                )}
              </Form.Item>
              <Form.Item>
                {getFieldDecorator('rangeDate', {})(
                  <RangePicker format={dateFormat} />
                )}
              </Form.Item>
              <Form.Item>
                <Button type='primary' icon='search' htmlType='submit' />
              </Form.Item>
            </Form>
          </Col>
          <Col span={1}>
            <ReactToPrint
              trigger={() => <Button icon="printer"></Button>}
              content={() => this.componentRef}
            />
          </Col>
        </Row>
        <Divider />
        <div ref={el => (this.componentRef = el)}>
          <Row gutter={16} style={{ margin: 10 }}>
            <Col span={16} >
              <Table
                bordered
                dataSource={this.state.dataSource}
                columns={this.state.columns}
                rowKey={'id'}
                pagination={false}/>
            </Col>
            <Col span={8}>
              <Card>
                <Row>
                  <Col span={12} style={{ textAlign: 'right' }}>Tổng doanh số:</Col>
                  <Col span={12} style={{ textAlign: 'right' }}>{this.state.totalDebt}</Col>
                </Row>
                <Row>
                  <Col span={12} style={{ textAlign: 'right' }}>Tổng doanh thu:</Col>
                  <Col span={12} style={{ textAlign: 'right' }}>{this.state.totalHistory}</Col>
                </Row>
                <Divider />
                <Row>
                  <Col span={12} style={{ textAlign: 'right' }}>Tổng công nợ:</Col>
                  <Col span={12} style={{ textAlign: 'right' }}>{this.state.totalRest}</Col>
                </Row>
              </Card>
            </Col>
          </Row>
        </div>
      </div>
    );
  }
}


function mapStateToProps(state) {
  return {};
}

export default connect(
  mapStateToProps,
)(Form.create()(Statistical));