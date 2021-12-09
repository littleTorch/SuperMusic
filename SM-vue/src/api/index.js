/**
 * 2
 * 引入关系
 * 
 * 
 */

import base from "./base"
import axios from "../utils/https"


const api = {
    getChengpin() {
        return axios.get(base.baseUrl + base.chengping);
    },
    getChengpin2() {
        return axios.get(base.baseUrl2 + base.chengping2);
    }, getChengpin3() {
        return axios.get(base.musicApi + base.musicA);
    }, getChengpin4(params) {
        return axios.get(base.musicApi + base.musicAB, {
            params: params
            //传参方式
        });
    }, getChengpin5(params) {
        return axios.get(base.musicApi + base.musicAC, {
            params: params
            //传参方式
        });
    }, getChengpin6() {
        return axios.get(base.musicApi + base.musicAbanner, {
            //传参方式
        });
    },
    getRaknkingList1() {
        return axios.get(base.musicApi + base.musicRlist1);
    }

}
export default api;






