module.exports = {
  "outputDir": "/Users/hyojaekim/Develop/chatbot/src/main/resources/static",
  "devServer": {
    "proxy": {
      "/api": {
        "target": "http://localhost:9000",
        "ws": true,
        "changeOrigin": true
      }
    }
  },
  "transpileDependencies": [
    "vuetify"
  ],
  module: {
    rules: [
      {
        test: /\.js/,
        use: ['vue-hot-reload-loader', 'buble-loader'],
        // If and only if all your components are in `path/to/components` directory
        include: path.resolve(__dirname, 'path/to/components')
      }
    ]
  }
}