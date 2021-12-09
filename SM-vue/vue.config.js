
module.exports = {
    devServer: {
        open:true,
        port: 80,
        hotOnly: false,
        proxy: {
               '/api': {
                target: 'http://localhost:8080',
                // 允许跨域
                changeOrigin: true,
                   ws: true,
                pathRewrite: {
                    '^/api': '/'
                }
            }
        }
    }
}