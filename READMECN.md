# WirelessKeyboard
A keyboard between two device. 

## 怎么安装？
**被控设备需要安装JRE**  
**控制设备需要安装Node.js**
1. 下载Release中的Device.jar到被控设备上
2. 下载Release中的Controller.tar.gz到控制设备上
3. 在控制设备上cd进入下载并解压好的文件夹
4. 在该目录下运行`npm install`来下载依赖文件

## 怎么使用？
1. 在控制设备上运行`node index.js`
2. 在被控设备上运行`java -jar Device.jar`
3. 然后就可以输入啦~

## 还有哪些问题？
- [ ] 不能使用`Ctrl+c`，不然会把控制端程序关掉。

**如果你有解决这些问题的方法请在QQ联系我！**
