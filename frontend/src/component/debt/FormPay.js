import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Form, InputNumber, Modal, Select} from "antd";
import {cashBack} from '../../service/MemberService';

const Option = Select.Option;

class FormPay extends Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        cashBack(values.idMember, values.price)
          .then(response => {
            if (response.data) {
              this.props.onClose()
            }
          })
          .catch(error => {
            console.log(error)
          })
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
                {this.props.members && this.props.members.map(member => (
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