import React, {Suspense} from 'react';
import './assets/css/App.css';
import {Layout, Menu, Spin} from 'antd';
import {Link, Route} from 'react-router-dom';
import Home from "./view/Home";
import Member from "./view/Member";
import Statistical from "./view/Statistical";

const {Header, Content} = Layout;

class App extends React.Component {
  state = {
    collapsed: false,
    current: 'home',
  };

  toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed,
    });
  };
  loading = () => {
    return (<Spin/>);
  };
  handleClick = (e) => {
    this.setState({
      current: e.key,
    });
  };

  render() {
    return (
      <Layout style={{width: '100%', height: '100%'}}>
        <Layout>
          <Header style={{background: '#fff', margin: '10px 16px 0px 16px', paddingLeft: 10}}>
            <Menu onClick={this.handleClick} selectedKeys={[this.state.current]} mode="horizontal">
              <Menu.Item key="home">
                <Link to="/">
                  <span>Trang chủ</span>
                </Link>
              </Menu.Item>
              <Menu.Item key="statistical">
                <Link to="/statistical">
                  <span>Đại lý</span>
                </Link>
              </Menu.Item>
              <Menu.Item key="member">
                <Link to="/member">
                  <span>Thành viên</span>
                </Link>
              </Menu.Item>
            </Menu>
          </Header>
          <Content
            style={{
              margin: '10px 16px 0px 16px',
              background: '#fff'
            }}>
            <Suspense fallback={this.loading}>
              <Route exact path="/" name="home" render={props => <Home {...props}/>}/>
              <Route exact path="/member" name="member" render={props => <Member {...props}/>}/>
              <Route exact path="/statistical" name="statistical" render={props => <Statistical {...props}/>}/>
            </Suspense>
          </Content>
        </Layout>
      </Layout>
    );
  }
}

export default App;
