import React from 'react';

const Home = React.lazy(() => import('../view/Home'));
const Member = React.lazy(() => import('../view/Member'));

const routes = [
  {path: '/', extra: true, name: 'Home', component: Home},
  {path: '/member', name: 'Member', component: Member},
];
export default routes;