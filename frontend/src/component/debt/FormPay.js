import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Form, InputNumber, Modal, Select, Spin} from "antd";
import {cashBack} from '../../service/MemberService';

const Option = Select.Option;

class FormPay extends Component {
  state = {
    loading: false
  }
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields(async (err, values) => {
      if (!err) {
        await this.setState({loading: true});
        await cashBack(values.idMember, values.price);
        await this.setState({loading: false});
        this.props.form.resetFields();
        this.props.onClose()
      }
    })
  };

  render() {
    const {getFieldDecorator} = this.props.form;
    return (
      <Form>
        <Modal
          title="Thanh Toán"
          visible={this.props.showModel}
          onOk={this.handleSubmit}
          onCancel={this.props.onClose}>
          <Spin spinning={this.state.loading}>
          <Form.Item>
            {getFieldDecorator('idMember', {
              rules: [{required: true, message: 'Chọn Thành viên'}],
            })(
              <Select
                style={{width: '100%'}}
                showSearch
                placeholder="Chọn thành viên"
                optionFilterProp="children"
                onSearch={this.onSearch}>
                {this.props.members && this.props.members.length > 0 && this.props.members.map(member => (
                  <Option key={member.id} value={member.id}>{member.fullName}</Option>
                ))
                }
              </Select>
            )}
          </Form.Item>
          <Form.Item>
            {getFieldDecorator('price', {
              rules: [{required: true, message: 'Nhập số'}],
            })(
              <InputNumber
                style={{width: '100%'}}
                placeholder="Số tiền"
                formatter={value => `${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
                parser={value => value.replace(/\$\s?|(,*)/g, '')}/>
            )}
          </Form.Item>
          </Spin>
        </Modal>
      </Form>
    );
  }
}

function mapStateToProps(state) {
  return {};
}

const FormPayCreate = Form.create()(FormPay);
export default connect(
  mapStateToProps,
)(FormPayCreate);