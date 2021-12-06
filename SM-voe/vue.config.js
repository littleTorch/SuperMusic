
module.exports = {
    devServer: {
        proxy: {
            '/api2': {
                // 此处的写法，目的是为了 将 /api 替换成 https://www.baidu.com/
                target: 'http://web.juhe.cn',
                // 允许跨域
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/api2': ''
                }
            }, '/te': {
                // 此处的写法，目的是为了 将 /api 替换成 https://www.baidu.com/
                target: 'http://apis.juhe.cn',
                // 允许跨域
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/te': ''
                }
            }, '/musicApi': {
                // 此处的写法，目的是为了 将 /api 替换成 https://www.baidu.com/
                target: 'http://localhost:3000',
                // 允许跨域
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/musicApi': ''
                }
            }
        }
    }
}