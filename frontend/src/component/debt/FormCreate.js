import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Form, Input, InputNumber, Modal, Select} from "antd";
import {createDebt} from '../../service/MemberService';

const Option = Select.Option;

class FormCreate extends Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        createDebt(values)
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
          title="Lấy Hàng"
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
                onSearch={this.onSearch}
                filterOption={(input, option) =>
                  option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                }>
                {this.props.members && this.props.members.length > 0 && this.props.members.map(member => (
                  <Option key={member.id} value={member.id}>{member.fullName}</Option>
                ))}
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
          <Form.Item>
            {getFieldDecorator('note')(
              <Input placeholder="Ghi chú"/>
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

const FormCreateCreate = Form.create()(FormCreate);
export default connect(
  mapStateToProps,
)(FormCreateCreate);