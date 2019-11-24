import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Select, Button, Form, Card} from 'antd';
import {DatePicker} from 'antd';
import moment from 'moment';

const {RangePicker} = DatePicker;
const dateFormat = 'YYYY/MM/DD';

const {Option} = Select;

function mapStateToProps(state) {
  return {};
}

class Home extends Component {

  onSearch = (val) => {
    console.log('search:', val);
  };

  handleSubmit = e => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
  };

  render() {
    const {getFieldDecorator, getFieldsError, getFieldError, isFieldTouched} = this.props.form;
    return (
      <div>
        <Card>
          <Form layout="inline" onSubmit={this.handleSubmit}>
            <Form.Item>
              {getFieldDecorator('person', {})(
                <Select
                  showSearch
                  style={{width: 200}}
                  placeholder="Select a person"
                  optionFilterProp="children"
                  onSearch={this.onSearch}
                  filterOption={(input, option) =>
                    option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                  }>
                  <Option value="jack">Jack</Option>
                  <Option value="lucy">Lucy</Option>
                  <Option value="tom">Tom</Option>
                </Select>
              )}
            </Form.Item>
            <Form.Item>
              {getFieldDecorator('rangeDate', {})(
                <RangePicker
                  format={dateFormat}
                />
              )}
            </Form.Item>
            <Form.Item>
              {getFieldDecorator('button', {})(
                <Button type='primary' icon='search'/>
              )}
            </Form.Item>
          </Form>
        </Card>
      </div>
    );
  }
}

export default connect(
  mapStateToProps,
)(Form.create()(Home));