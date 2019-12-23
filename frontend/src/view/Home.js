import React, { Component } from 'react';
import { connect } from 'react-redux';
import { DatePicker, Button, Col, Form, Card, Icon, Popconfirm, Row, Select, Table } from 'antd';
import { deleteDebt, deleteHistory, getAgency, getListDebt, getMemberOf } from '../service/MemberService'
import FormCreateCreate from "../component/debt/FormCreate";
import Divider from "antd/es/divider";
import moment from 'moment';
import NumberFormat from 'react-number-format';
import FormPayCreate from "../component/debt/FormPay";
import ReactToPrint from 'react-to-print';

const { Column } = Table;

const { RangePicker } = DatePicker;
const dateFormat = 'DD-MM-YYYY';
const { Option } = Select;

class Home extends Component {

  state = {
    agencies: [],
    members: [],
    showModel: false,
    debts: [],
    rest: 0,
    totalDebt: 0,
    totalHistory: 0,
    histories: [],
    modalPay: false,
    condition: null,
    flgPrint: false
  };

  UNSAFE_componentWillMount() {
    getAgency()
      .then(response => {
        this.setState({ agencies: response.data })
      })
      .catch(error => {

      })
  }

  handleSubmit = e => {
    e.preventDefault();
    this.props.form.validateFields(async (err, values) => {
      if (!err) {
        await this.setState({ condition: values });
        this.loadDebt();
      }
    });
  };

  loadDebt() {
    if (!this.state.condition) return;
    getListDebt(this.state.condition)
      .then((response) => {
        this.setState({
          debts: response.data.debts,
          totalHistory: response.data.totalHistory,
          totalDebt: response.data.totalDebt,
          rest: response.data.rest,
          histories: response.data.histories
        });
      }).catch(error => {
        console.log(error)
      })
  };

  showModel = () => {
    this.setState({ showModel: true })
  };
  onCloseModal = () => {
    this.setState({ showModel: false });
    this.loadDebt();
  };
  showModalPay = () => {
    this.setState({ modalPay: true })
  };
  onCloseModalPay = () => {
    this.setState({ modalPay: false });
    this.loadDebt();
  };
  handleDeleteHistory = (id) => {
    deleteHistory(id)
      .then(() => {
        this.loadDebt();
      })
      .catch((error) => console.log(error));
  };

  handleDeleteDebt = (id) => {
    deleteDebt(id).then(() => {
      this.loadDebt()
    })
  };

  onChangeAgency = (value, { props }) => {
    getMemberOf(value)
      .then((response) => {
        this.setState({ members: response.data })
      })
  };

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <div>
        <div style={{ margin: 30 }}>
          <Row>
            <Col span={23}>
              <Form layout="inline" onSubmit={this.handleSubmit}>
                <Form.Item>
                  {getFieldDecorator('agencyId', {})(
                    <Select
                      showSearch
                      style={{ width: 200 }}
                      placeholder="Chọn Nhân Viên"
                      optionFilterProp="children"
                      onChange={this.onChangeAgency}
                      filterOption={(input, option) =>
                        option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                      }>
                      {
                        this.state.agencies && this.state.agencies.length > 0 && this.state.agencies.map(member => (
                          <Option key={member.id} value={member.id}>{member.fullName}</Option>
                        ))
                      }
                    </Select>
                  )}
                </Form.Item>
                <Form.Item>
                  {getFieldDecorator('memberId', {})(
                    <Select
                      showSearch
                      style={{ width: 200 }}
                      placeholder="Chọn Đại lý"
                    >
                      {
                        this.state.members && this.state.members.length > 0 && this.state.members.map(member => (
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
                trigger={() => <Button icon="printer"/>}
                content={() => this.componentRef}
              />
            </Col>
          </Row>
          <Button icon='plus' onClick={this.showModel} style={{ marginRight: 10 }}>Lấy Hàng</Button>
          <Button icon='plus' onClick={this.showModalPay}>Thanh Toán</Button>
        </div>
        <Divider />
        <div style={{ margin: 10 }}>
          <Row gutter={8}>
            <Col className="gutter-row" span={10}>
              <Row>
                <Col span={12}><h4>Lấy Hàng</h4></Col>
              </Row>
              <Table dataSource={this.state.debts} rowKey="id" bordered={true} pagination={false}>
                <Column title="Ngày Lấy" dataIndex="createdDate" key="createdDate"
                  render={createDate => (<span>{moment(createDate).format(dateFormat)}</span>)} />
                <Column title="Số tiền" dataIndex="price" key="price"
                  render={price => (
                    <NumberFormat displayType={'text'} thousandSeparator={true} value={price} />)} />
                <Column title="Ghi Chú" dataIndex="note" key="note" />
                <Column title="Tuỳ chọn" dataIndex="action" key="action"
                  render={(text, record) => (
                    this.state.debts.length > 0 ? (
                      <Popconfirm title="Sure to delete?" onConfirm={() => this.handleDeleteDebt(record.id)}>
                        <Icon type="delete" />
                      </Popconfirm>
                    ) : null
                  )} />
              </Table>
            </Col>
            <Col className="gutter-row" span={8}>
              <Row>
                <Col span={12}><h4>Thanh Toán</h4></Col>
              </Row>
              <Table dataSource={this.state.histories} rowKey="id" bordered={true} pagination={false}>
                <Column title="Ngày thanh toán" dataIndex="createdDate" key="createdDate"
                  render={date => (<span>{moment(date).format(dateFormat)}</span>)} />
                <Column title="Số tiền" dataIndex="price" key="price"
                  render={price => (
                    <NumberFormat displayType={'text'} thousandSeparator={true} value={price} />)} />/>
                <Column title="Tuỳ chọn" dataIndex="action" key="action"
                  render={(text, record) => (
                    this.state.histories.length > 0 ? (
                      <Popconfirm title="Sure to delete?" onConfirm={() => this.handleDeleteHistory(record.id)}>
                        <Icon type="delete" />
                      </Popconfirm>
                    ) : null
                  )} />
              </Table>
            </Col>
            <Col className="gutter-row" span={6}>
              <h4>Thống kê</h4>
              <Card size={'small'}>
                <Row>
                  <Col span={12} style={{ textAlign: 'right' }}>Nợ:</Col>
                  <Col span={12}>
                    <div style={{ textAlign: 'right' }}>
                      <NumberFormat
                        displayType={'text'}
                        thousandSeparator={true}
                        value={this.state.totalDebt} />
                    </div>
                  </Col>
                </Row>
                <Row>
                  <Col span={12} style={{ textAlign: 'right' }}>Thanh toán:</Col>
                  <Col span={12}>
                    <div style={{ textAlign: 'right' }}>
                      <NumberFormat
                        displayType={'text'}
                        thousandSeparator={true}
                        value={this.state.totalHistory} />
                    </div>
                  </Col>
                </Row>
                <Row>
                  <Divider />
                  <Col span={12} style={{ textAlign: 'right' }}>Còn nợ lại:</Col>
                  <Col span={12}>
                    <div style={{ textAlign: 'right' }}>
                      <NumberFormat
                        displayType={'text'}
                        thousandSeparator={true}
                        value={this.state.rest} />
                    </div>
                  </Col>
                </Row>
              </Card>
            </Col>
          </Row>
        </div>
        <FormCreateCreate
          showModel={this.state.showModel}
          onClose={this.onCloseModal}
          members={this.state.members} />
        <FormPayCreate
          showModel={this.state.modalPay}
          onClose={this.onCloseModalPay}
          members={this.state.members} />
        <div style={{ display: this.state.flgPrint ? "show" : "none" }}>
          <div style={{ margin: 10 }} ref={el => (this.componentRef = el)}>
            <Row gutter={8}>
              <Col className="gutter-row" span={10}>
                <Row>
                  <Col span={12}><h4>Lấy Hàng</h4></Col>
                </Row>
                <Table dataSource={this.state.debts} rowKey="id" bordered={true} pagination={false}>
                  <Column title="Ngày Lấy" dataIndex="createdDate" key="createdDate"
                    render={createDate => (<span>{moment(createDate).format(dateFormat)}</span>)} />
                  <Column title="Số tiền" dataIndex="price" key="price"
                    render={price => (
                      <NumberFormat displayType={'text'} thousandSeparator={true} value={price} />)} />
                  <Column title="Ghi Chú" dataIndex="note" key="note" />
                </Table>
              </Col>
              <Col className="gutter-row" span={8}>
                <Row>
                  <Col span={12}><h4>Thanh Toán</h4></Col>
                </Row>
                <Table dataSource={this.state.histories} rowKey="id" bordered={true} pagination={false}>
                  <Column title="Ngày thanh toán" dataIndex="createdDate" key="createdDate"
                    render={date => (<span>{moment(date).format(dateFormat)}</span>)} />
                  <Column title="Số tiền" dataIndex="price" key="price"
                    render={price => (
                      <NumberFormat displayType={'text'} thousandSeparator={true} value={price} />)} />/>
                </Table>
              </Col>
              <Col className="gutter-row" span={6}>
                <h4>Thống kê</h4>
                <Card size={'small'}>
                  <Row>
                    <Col span={12} style={{ textAlign: 'right' }}>Nợ:</Col>
                    <Col span={12}>
                      <div style={{ textAlign: 'right' }}>
                        <NumberFormat
                          displayType={'text'}
                          thousandSeparator={true}
                          value={this.state.totalDebt} />
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Col span={12} style={{ textAlign: 'right' }}>Thanh toán:</Col>
                    <Col span={12}>
                      <div style={{ textAlign: 'right' }}>
                        <NumberFormat
                          displayType={'text'}
                          thousandSeparator={true}
                          value={this.state.totalHistory} />
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Divider />
                    <Col span={12} style={{ textAlign: 'right' }}>Còn nợ lại:</Col>
                    <Col span={12}>
                      <div style={{ textAlign: 'right' }}>
                        <NumberFormat
                          displayType={'text'}
                          thousandSeparator={true}
                          value={this.state.rest} />
                      </div>
                    </Col>
                  </Row>
                </Card>
              </Col>
            </Row>
          </div>
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
)(Form.create()(Home));