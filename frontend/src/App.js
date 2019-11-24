import React, {Suspense} from 'react';
import './assets/css/App.css';
import {Icon, Layout, Menu, Spin} from 'antd';
import {Link, Route} from 'react-router-dom';
import Home from "./view/Home";
import Member from "./view/Member";

const {Header, Content, Footer, Sider} = Layout;

class App extends React.Component {
  state = {
    collapsed: false,
  };

  toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed,
    });
  };
  loading = () => {
    return (<Spin/>);
  };

  render() {
    return (
      <Layout style={{width: '100%', height: '100%'}}>
        <Sider trigger={null} collapsible collapsed={this.state.collapsed}>
          <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
            <Menu.Item key="1">
              <Link to="/">
                <Icon type="home"/>
                <span>Trang chủ</span>
              </Link>
            </Menu.Item>
            <Menu.Item key="2">
              <Link to="/member">
                <Icon type="user"/>
                <span>Thành viên</span>
              </Link>
            </Menu.Item>
          </Menu>
        </Sider>
        <Layout>
          <Header style={{background: '#fff', margin: 10, paddingLeft: 10}}>
            <Icon
              className="trigger"
              type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'}
              onClick={this.toggle}
            />
          </Header>
          <Content
            style={{
              margin: '24px 16px',
              padding: 24,
              background: '#fff',
              minHeight: 280,
            }}
          >
            <Suspense fallback={this.loading}>
              <Route exact path="/" name="Home" render={props => <Home {...props}/>}/>
              <Route exact path="/member" name="Member" render={props => <Member {...props}/>}/>
            </Suspense>
          </Content>
          <Footer style={{textAlign: 'center'}}>©2019 Created by ntduoc</Footer>
        </Layout>
      </Layout>
    );
  }
}

export default App;
