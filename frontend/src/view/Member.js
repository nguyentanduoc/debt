import React, {Component} from 'react';
import {connect} from 'react-redux';

function mapStateToProps(state) {
  return {};
}

class Member extends Component {
  render() {
    return (
      <div>
        Member
      </div>
    );
  }
}

export default connect(
  mapStateToProps,
)(Member);