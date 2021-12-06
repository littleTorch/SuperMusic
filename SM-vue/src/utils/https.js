/*
封装axios

*/

import axios from "axios"
import qs from "qs"


//声明错误信息
const errorHandle = (status,other) => {
    switch(status){
        case 400:
            //请求次数限制
            console.log("服务器请求限制");
            break;
        case 401:
            console.log("银魂信息验证失败");
            break;
        case 403:
            console.log("请求被限制");
            break;
        case 404:
            console.log("客户端错误");
            break;
        default:
            console.log(other);
            break;
        
    }
}

const instance = axios.create({
    timeout:3000
})

instance.defaults.baseURL = "";
instance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';


//拦截器


// 添加请求拦截器
instance.interceptors.request.use(config => {
    // 在发送请求之前做些什么
    if(config.method === 'post'){
        config.data = qs.stringify(config.data);
    }
    return config;
  }, error => Promise.reject(error));// 对请求错误做些什么

// 添加响应拦截器
instance.interceptors.response.use(response => {
    console.log(response)

    return response.status === 200 ? Promise.resolve(response) : Promise.reject(response)
 
},
      error => {
        const { response } = error;
        if(response){
            errorHandle(response.status,response.data)
            return Promise.reject(response)
        }else{
            //response不存在，服务器
            console.log("请求被中断");
        }
    }
 );


//导出
export default instance








