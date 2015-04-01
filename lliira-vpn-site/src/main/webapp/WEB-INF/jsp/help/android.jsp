
<div class=WordSection1>

<p><b><span style='font-size:16.0pt;line-height:
115%;'>在</span></b><b><span style='font-size:16.0pt;line-height:
115%'>Android</span></b><b><span style='font-size:16.0pt;line-height:
115%;'>下使用</span></b><b><span style='font-size:16.0pt;line-height:
115%'>VPN</span></b></p>

<p><img width=576 height=4 src="images/help/android/image001.gif"></p>

<p><span >简介</span><span
>: </span><span >目前在</span><span
>Android</span><span >下使用我们的</span><span >VPN</span><span
>主要有以下几种方式，您可以选择适合方式进行连接。</span></p>

<p><span style='color:navy'>1. <a href="#method_1">使用L2TP连接VPN （Android2.3或2.3以上</a></span></p>
<p><span style='color:navy'>2. <a href="#method_2">使用IPSec Xauth PSK连接VPN（Android4.0或4.0以上）</a></span></p>

<p><span style='color:navy'>3. <a href="#method_3">使用Cisco官方的Cisco&nbsp;AnyConnect (Android4.0或4.0以上）</a></span></p>

<p><img border=0 width=576 height=4
src="images/help/android/image001.gif"></p>

<p>
  <a name="method_1">
<span style='color:navy'>方法一：</span><span
style='color:navy'>   </span><span style='color:navy'>使用</span><span
style='color:navy'>L2TP</span><span style='
color:navy'>连接</span><span style='color:navy'>VPN, （必须是Android 2.3 或2.3以上）</span>
  </a>
 <font color="red"> <b>（服务器请填写 216.218.196.158） </font> </b>
</p>

<p>1.<span style=''>点击「设置」，然后单击「无线和网络」，点击「虚拟专用网络设置」，然后单击「添加虚拟专用网」</span></p>

<p><img border=0 width=183 height=212
src="images/help/android/image002.jpg"><img border=0 width=179 height=240
src="images/help/android/image003.jpg"></p>

<p>2.&nbsp;<span style=''>选择「添加</span>VPN&nbsp;“L2TP/IPSec&nbsp;PSK”<span
 style=''>」</span></p>

<p><img border=0 width=204 height=207
src="images/help/android/image004.jpg"></p>

<p>3.&nbsp;<span style=''>点击「虚拟专用网名」。输入任意字符串，例如：</span>iqlink<span
 style=''>。点击「确定」</span></p>

<p><img border=0 width=201 height=105
src="images/help/android/image005.jpg"><img border=0 width=165 height=209
src="images/help/android/image006.jpg"></p>

<p>4&nbsp;<span style=''>点击「设置虚拟专用网络服务器」，输入您要连接的服务器地址，例如：</span>&nbsp;iqlink1.us<span
 style=''>、</span>iqlink2.us<span
style=''>、</span>iqlink3.us<span style='font-family:
宋体'>等。请先确定您有访问该服务器的权限。</span></p>

<p><img border=0 width=331 height=244
src="images/help/android/image007.jpg"></p>

<p>5.&nbsp;<span style=''>点击「设置的</span>IPSec<span
 style=''>预共享密钥」，然后输入</span>iqlink<span
style=''>。点击「确定」，请注意该密钥为全部写。</span></p>

<p><img border=0 width=187 height=219
src="images/help/android/image008.jpg"></p>

<p>6.&nbsp;<span style=''>点击菜单，然后点击「保存」。</span></p>

<p>7.&nbsp;<span style=''>在你的</span>VPN<span
 style=''>列表，标签</span>iqlink<span
style=''>。在「用户名」和「密码」字段中，输入您在我们网站</span>www.iqlinkvpn.com<span
 style=''>中使用的用户名（</span><span
style=''>⼀</span><span style=''>一般为邮件地址）和密码。请确认勾选上「记住用户名」。点击「连接」。</span></p>

<p><img border=0 width=206 height=252
src="images/help/android/image009.jpg"></p>

<p><span style=''>注意：您可能会提示输入密码</span>“<span
 style=''>启用证书存储</span>”.&nbsp;<span
style=''>三次失败，你可以重置这个密码。</span></p>

<p><img border=0 width=576 height=4
src="images/help/android/image001.gif"></p>
<p>
  <a name="method_2">
<span style='color:navy'>方法二：使用IPSec Xauth PSK模式登陆的，Android4.0以上（2.3不支持），</a>
 <font color="red"> <b>（服务器请填写 216.218.196.158） </font> </b>
</p>
<p><span style=''> <b>　以下是Android4.0系统IPSec Xauth PSK配置说明　</b> </span></p>
<p><b>　1. </b>打开设置选择，点击 “更多”  </p>
<p><img border=0 width=250 height=380　
src="images/help/android/image501.jpg"></p>
<p><b>　2. </b>点击下图所示的（红色圈选）那里</p>
<p><img border=0 width=250 height=380　
src="images/help/android/image502.jpg"></p>
<p><b>　3. </b>点击“添加...网络”：</p>
<p><img border=0 width=250 height=380　
src="images/help/android/image503.jpg"></p>
<p><b>　4. </b>设置具体信息（如下图＋详解的输入信息）：</p>
<p><img border=0 width=250 height=380　
src="images/help/android/image504.jpg"></p>
<p><b>　5. </b>输入用户名、密码；保存帐户信息，再点击“连接：</p>
<p><img border=0 width=250 height=380　
src="images/help/android/image505.jpg"></p>
<p> 这种方式的优点是无需任何第三方客户端，且可以和L2TP/IPSec PSK方式统一放在系统VPN里一起配置，方便切换不同的模式，
不过推荐首选这个模式而非L2TP。</p>

<p>上面的办法虽然用了系统内置的VPN，但是Android有个非常讨厌的地方就是一旦配置VPN，就需要加上解锁的步骤，即使你用pattern模式画图解锁，仍然没有直接用各种locker舒心。当然也有各种办法（sqlite3改写db数据，输错密码用id验证等）去掉这个限制，但没有一个是真正完善的方案。所以如果你不需要L2TP，则可以直接禁用内置VPN而通过第三方工具VpnCilla来管理你的IPSec Xauth PSK方式（仅支持4.0）。 第三方工具VpnCilla市场试用版下载处是https://play.google.com/store/apps/details?id=com.gmail.mjm4456.vpncillatrial
</p>

<p> 感谢网友 Jerry.cmlx 为我们提供如上VPN使用指南;  </p>

<p><img border=0 width=576 height=4
src="images/help/android/image001.gif"></p>
<p>
  <a name="method_2">
  <span style='color:navy'>方法三： 使用官方的 Cisco AnyConnect 连接</span>
  </a>
   <font color="red"> <b>（服务器请填写 64.71.190.198:444） </font> </b>
</p>

<p><span style=''>如果你的安卓版本低于 4.0， 请先root你的手机。 低于4.0的安卓手机， 要装 AnyConnect App，必须有root的权限。 如何root，请参考 <a href="http://www.androidcentral.com/root"> http://www.androidcentral.com/root </a> （若有网友，知道好的中文root网站，请发电邮给我们 iqlinkvpn@yahoo.com)。 root后，请下载并安装  <a href="https://play.google.com/store/apps/details?id=com.cisco.anyconnect.vpn.android.rooted"> Rooted AnyConnect </a>　</span></p>

<p><span style=''>　高于4.0的安卓手机，不必root你的手机。请下载并安装  <a href="https://play.google.com/store/apps/details?id=com.cisco.anyconnect.vpn.android.avf&hl=en"> AnyConnect　ICS+　</a>　</span></p>

<p><span style=''> <b>　以下是Android4.0系统的配置说明　</b> </span></p>
<p><span style=''>　<b> 1.</b>   下载AnyConnect ICS+，地址是　<a href="https://play.google.com/store/apps/details?id=com.cisco.anyconnect.vpn.android.avf&hl=en"> AnyConnect　ICS+　</a></span></p>
<p><span style=''>　<b>2. </b> 把下载好的AnyConnect ICS+ v3.0.09093.apk安装到手机
<p><img border=0 width=250 height=380　
src="images/help/android/image011.jpg"></p>
<p><span style=''>　<b>3. </b> 打开手机上的AnyConnect客户端，点击“添加新的VPN连接”
<p><img border=0 width=250 height=380　
src="images/help/android/image013.jpg"></p>
<p><span style=''>　<b>4. </b> 在“连接编辑器里”，找到“说明”，设置你自己想要的名称， 比如 iqlink1 ，找到“服务器地址”，填写例如： iqlink1.us:444、iqlink2.us:444、iqlink3.us:444等。请先注册登陆; 并查它们的IP地址； 请用IP地址连， 比如iqlink1.us:444 是 64.71.190.198:444 </p>
<p><img border=0 width=250 height=380　
src="images/help/android/image015.jpg">
<img border=0 width=250 height=380　
src="images/help/android/image017.jpg">
</p>
<p><span style=''><b>　5. </b> 找到主界面右上角的按键，点击后找到“设置”，在“阻止不信任的服务器”里把勾去掉. </p>
<p><img border=0 width=250 height=380　
src="images/help/android/image019.jpg"></p>
<p><span style=''><b>　6. </b> 返回后点击刚刚设置的名称,比如 iqlink1 ，这时会出现“请输入您的用户名和密码”
<p><img border=0 width=250 height=380　
src="images/help/android/image021.jpg"></p>
<p><span style=''><b>　7. </b> 这时你会看到“正在连接，请稍后”</p>
<p><img border=0 width=250 height=380　
src="images/help/android/image023.jpg"></p>
<p><span style=''><b>　8. </b> 会出现“安全警告：证书不可信”，点击“继续”.  我们注册并安装了自己的证书。但证书是和域名绑在一起的。 由于你只能用IP地址连接我们的服务器，故每次连接时都会出现此警告。现在没办法，请你相信我们，忽略此警告。 
<p><img border=0 width=200 height=380　
src="images/help/android/image025.jpg"></p>
以上Android-AnyConnect的使用指南，由网友“小沈阳”为我们提供，谢谢。
</div>
