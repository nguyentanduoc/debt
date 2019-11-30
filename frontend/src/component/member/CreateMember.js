import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Button, Card, Form, Icon, Input, Select} from "antd";
import {createUser, getAgency} from '../../service/MemberService'

class CreateMember extends Component {

  state = {
    agencies: []
  };

  UNSAFE_componentWillMount() {
    getAgency()
      .then(response => {
        this.setState({agencies: response.data})
      })
      .catch(error => {

      })
  }

  createMember = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        createUser(values)
          .then((response) => {
            this.props.createSuccess();
          })
          .catch((err) => {
            console.log(err);
          })
      }
    });
  };

  render() {
    const {getFieldDecorator} = this.props.form
    return (
      <div style={{padding: 10}}>
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
              {getFieldDecorator('memberOf')(
                <Select
                  showSearch
                  placeholder="Thuộc Thành viên"
                >
                  {this.state.agencies && this.state.agencies.length > 0 && this.state.agencies.map(member => (
                    <Select.Option key={member.id} value={member.id}>{member.fullName}</Select.Option>
                  ))
                  }
                </Select>
              )}
            </Form.Item>
            <Form.Item>
              {getFieldDecorator('email')(
                <Input
                  prefix={<Icon type="mail" style={{color: 'rgba(0,0,0,.25)'}}/>}
                  type="email"
                  placeholder="Địa chỉ Mail"
                />
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
      </div>
    );
  }
}

function mapStateToProps(state) {
  return {};
}

const CreateMemberForm = Form.create()(CreateMember);
export default connect(
  mapStateToProps,
)(CreateMemberForm);