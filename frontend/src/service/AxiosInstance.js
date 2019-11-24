import Axios from 'axios';

export default Axios.create({
  baseURL: 'http://localhost:8090/api',
  timeout: 1000
});