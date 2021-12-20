
module.exports = {
    devServer: {
        open:true,
        port: 80,
        hotOnly: false,
        disableHostCheck: true,
        proxy: {
               '/api': {
                target: 'http://localhost:8080',
                   //公网地址
                   // http://sm2.v.frp.fit，
                // 允许跨域
                changeOrigin: true,
                   // ws: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}