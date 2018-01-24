
一些基础的自定义
各个自定义控件都可以单独拷贝使用
为满足项目风格，只需要进行简单的修改即可

CustomView 包中主要是一些自定义view
   暂时自定义的有
   {"自定义顶部标题栏", "百分比饼图", "自定义输入框", "验证码", "标签云"};

   
Dialog 包中主要是自定义或者重写各类dialog
   目前主要的dialog有
   {"提示弹框", "Spinner弹框", "Progress弹框", "DatePicker弹框"}
   

Translucent 包中是沉浸式
	主要有三步：
	1、在value中的style里面定义TranslucentTheme主题；
	2、在Androidmanifest.xml中引用；
	3、在布局文件中设置android:fitsSystemWindows="true"即可

