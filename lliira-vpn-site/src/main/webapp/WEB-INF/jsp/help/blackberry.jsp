
<div class=WordSection1>

<p><b><span style='font-size:16.0pt;line-height:115%;'>在BlackBerry设备上使用VPN</span></b></p>

<p><img width=576 height=4 src="images/help/android/image001.gif"></p>

<p><span >简介: 在黑莓手机和平板上使用内置 Cisco Secure PIX Firewall VPN </span></p>

<p><span style='color:navy'>1. 在黑莓手机上使用内置 Cisco Secure Pix Firewall VPN；
</span></p>

<p><span style='color:navy'>2.在黑莓平板上使用内置 Cisco Secure Pix Firewall VPN </span></p>


<p><span style='color:navy'>3.在黑莓 OS 6.0 上使用内置 Cisco Secure Pix Firewall VPN </span></p>
<p><img border=0 width=576 height=4
src="images/help/android/image001.gif"></p>

<p>
  <a name="method_1">
<span style='color:navy'>一：</span><span
style='color:navy'>   </span><span style='color:navy'><b> 在黑莓手机上使用内置 Cisco Secure Pix Firewall VPN</b> </span>
  </a><font color="red"> <b>（服务器请填写 216.218.196.158） </font> </b>
</p>

<p>1. &nbsp; 首先需要建立一个新的VPN连接; 打开选项-安全选项-高级安全选项-vpn; 按下黑莓菜单键，找到“新建”进入，选择“cisco 安全 pix 防火墙 VPN”。
</p>

<p><img border=0 width=340 height=340 src="images/help/blackberry/image001.jpg"></p>
<p><img border=0 width=340 height=340 src="images/help/blackberry/image003.jpg"></p>

<p>2.&nbsp;<span style=''>输入以下参数：</span></p>

<p> &nbsp; &nbsp; 名称：iqlink  </p>

<p> &nbsp; &nbsp;集中器ip地址：iqlink11.us(服务器地址可以为iqlink1.us、iqlink2.us、iqlink3.us、iqlink4.us、iqlink5.us等，请确定您有访问该服务器的权限)。</p>
<p> &nbsp; &nbsp; 组名：iqlink </p>
<p> &nbsp; &nbsp; 组密码: iqlink </p>
<p> &nbsp; &nbsp; 用户名: 您在iqlinkvpn.com注册所用的电子邮件地址 </p>
<p> &nbsp; &nbsp; 用户密码: 您在iqlinkvpn.com所用的密码 </p>
<p> &nbsp; &nbsp; 勾选保存密码短语</p>

<p><img border=0 width=340 height=340
src="images/help/blackberry/image005.jpg"></p>

<p>3.&nbsp;<span style=''>勾选动态确认DNS </span></p>

<p> &nbsp; &nbsp; 勾选启用拓展验证  </p>

<p> &nbsp; &nbsp; IKE DH组：第2组 </p>

<p> &nbsp; &nbsp; IKE 秘钥：3DES（168位秘钥）</p>

<p> &nbsp; &nbsp; IKE 无用数据：HMAC SHA1 (160)位 </p>

<p> &nbsp; &nbsp; “完全正向保密”不勾选 </p>

<p> &nbsp; &nbsp; IPSec 加密和无用数据组：3DS-SHA1 </p>


<p><img border=0 width=301 height=301
src="images/help/blackberry/image007.jpg"></p>
<p> <img border=0 width=301 height=301
src="images/help/blackberry/image009.jpg"></p>

<p>4&nbsp;<span style=''> 其余默认保存退出。</span></p>

<p> &nbsp; &nbsp; 然后打开 WiFi 设置，编辑当前使用的 WiFi Profile </p>

<p> &nbsp; &nbsp; 在最底下有个 VPN，选择刚刚创建的 VPN profile </p>

<p> &nbsp; &nbsp; 保存后 VPN 会被自动激活 </p>
 
<p> &nbsp; &nbsp; 每次 WiFi 启用都会自动打开VPN。 </p>


<p>
  <a name="method_2">
  <span style='color:navy'><b>二： 在黑莓 Playbook 上使用内置 Cisco Secure PIX firewall VPN</b> </span>
  </a><font color="red"> <b>（服务器请填写 216.218.196.158） </font> </b>
</p>

<p> <span style=''> 新建VPN： 进入option – security – VPN - add new </span></p>

<p> &nbsp; &nbsp; Profile name: iqlink </p>

<p> &nbsp; &nbsp;Server address: iqlink11.us(服务器地址可以为iqlink1.us、iqlink2.us、iqlink3.us、iqlink4.us、iqlink5.us等，请确定您有访问该服务器的权限)。 </p>

<p> &nbsp; &nbsp; Gateway type : cisco secure pix firewall VPN </p>

<p> &nbsp; &nbsp;Authentication type : XAUTH-PSK </p>

<p> &nbsp; &nbsp;Group username : iqlink </p>

<p> &nbsp; &nbsp;Group password : iqlink </p>

<p> &nbsp; &nbsp;不勾选Hard token </p>

<p> &nbsp; &nbsp;Username : 您在iqlinkvpn.com注册所用的电子邮件地址 </p>

<p> &nbsp; &nbsp;Password : 您在iqlinkvpn.com所用的密码</p>

<p><img border=0 width=604 height=407
src="images/help/blackberry/image011.jpg"></p>

<p><img border=0 width=604 height=407
src="images/help/blackberry/image013.jpg"></p>

<p>
  <span style='color:navy'><b>三： 在黑莓 OS 6.0 上使用内置 Cisco Secure PIX firewall VPN</b> </span><font color="red"> <b>（服务器请填写 216.218.196.158） </font> </b>
</p>
<p>&nbsp; &nbsp; 重要的是一定要勾上 use hard token 才可以連接。黑莓Bold 97809780 OS6.0 system 已测试。 其它設置按照教程不變</p>
<p><img border=0 width=604 height=407
src="images/help/blackberry/image015.jpg"></p>

<p><b> 最后：</b> 英文版的黑莓VPN使用指南在 <a href="http://btsc.webapps.blackberry.com/btsc/viewdocument.do?noCount=true&externalId=KB13469&sliceId=1&dialogID=177854196&cmd=displayKC&docType=kc&stateId=1+0+177840874&ViewedDocsListHelper=com.kanisa.apps.common.BaseViewedDocsListHelperImpl"> 这里</a> </p>
<p> &nbsp; &nbsp; 感谢网友 Edward 为我们提供如上黑莓VPN使用指南; 他的微博是 <a href="http://www.weibo.com/penghui222?source=webim"> Penghui_</a>； 同时感谢网友小柯提供OS6.0的设置。</p>
</div>
