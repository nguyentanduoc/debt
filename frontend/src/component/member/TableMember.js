import React, {Component} from 'react'
import {connect} from 'react-redux'
import {getAllMember} from '../../service/MemberService'
import {Card, Table} from 'antd'
import moment from 'moment'

class TableMember extends Component {
  state = {
    columns: [
      {
        title: 'Tên thanh viên',
        dataIndex: 'fullName',
        key: 'fullName',
      },
      {
        title: 'Email',
        dataIndex: 'email',
        key: 'email',
      },
      {
        title: 'Address',
        dataIndex: 'address',
        key: 'address',
      },
      {
        title: 'Ngày tạo',
        dataIndex: 'createAt',
        key: 'createAt',
        render: text => {moment(text).format("DD-MM-YYYY")},
      },
    ],
    members: null
  };

  componentWillMount() {
    this.loadMember()
  }
  componentDidMount() {
    const { childRef } = this.props;
    childRef(this);
  }

  loadMember = () => {
    getAllMember()
      .then((response) => {
        this.setState({members: response.data})
      })
      .catch(err => {
        console.log(err)
      })
  }

  render() {
    return (
      <Card>
        <Table columns={this.state.columns} dataSource={this.state.members} rowKey={'id'} bordered={true}/>
      </Card>
    );
  }
}

function mapStateToProps(state) {
  return {};
}

export default connect(
  mapStateToProps,
)(TableMember);