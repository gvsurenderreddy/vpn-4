
<div class=WordSection1>

<p><b><span style='font-size:16.0pt;line-height:115%;'>在Windows 8 设备上使用VPN</span></b></p>

<p><img width=576 height=4 src="images/help/android/image001.gif"></p>

<p><span > 在Windows8上，你有三种方法使用VPN： </span></p>
<p><img border=0 width=576 height=4
src="images/help/android/image001.gif"></p>
<p><span style='color:navy'>方式一：下载并使用 Cisco VPN Client：<a
			href="downloads/vpnclient-win-msi-5.0.07.0410-k9.exe">vpnclient-win-msi-5.0.07.0410-k9.exe</a>; 或许你需要下载64-bit 的Cisco VPN Client：<a
			href="downloads/vpnclient-winx64-msi-5.0.07.0440-k9.exe">vpnclient-winx64-msi-5.0.07.0410-k9.exe</a>。 然后请参考视频进行安装和设置 <a href="http://www.youtube.com/watch?v=bg-qv_muows"> Cisco IPSec VPN Client on Windows 8 </a> 。 设置时请用IPSec Over TCP, TCP 端口可用123 或 10000； 参见 Window中方法二 Cisco VPN Client说明。 有网友设置并连接成功，如下所示。 </span></p>
<p> <img border=0 width=901 height=301
src="images/help/blackberry/image101.jpg"></p>
<p><span > 具体步骤如下：</span></p>
<p><span > 1： 下载并使用 Cisco VPN Client：<a
			href="downloads/vpnclient-win-msi-5.0.07.0410-k9.exe">vpnclient-win-msi-5.0.07.0410-k9.exe</a>; 或许你需要下载64-bit 的Cisco VPN Client：<a
			href="downloads/vpnclient-winx64-msi-5.0.07.0440-k9.exe">vpnclient-winx64-msi-5.0.07.0410-k9.exe</a></span></p>
<p> <img border=0 width=601 height=301
src="images/help/blackberry/image102.jpg"></p>
<p><span > 2： 设置Cisco VPN Client 请参考Windows的方式二。即 a）选择 New 按钮新建VPN连接 b）Name,Password和Conﬁrm Password都输入iqlink；服务器请填写 216.218.196.158 </p>
<p><span > 3： 若连接你会得到“Failed to enable Virtual Adapter" 错误。 </p>
<p> <img border=0 width=301 height=101
src="images/help/blackberry/image103.jpg"></p>
<p><span > 3： 运行 regedit 修改 HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\CVirtA\DisplayName 文件</p>
<p> <img border=0 width=301 height=151
src="images/help/blackberry/image104.jpg"></p>			
<p> <img border=0 width=901 height=101
src="images/help/blackberry/image105.jpg"></p>
<p><span > 4： 去掉 Cisco System VPN Adapter 前面的乱码；就好了！</p>			
<p> <img border=0 width=901 height=501
src="images/help/blackberry/image106.jpg"></p>

<p><img border=0 width=576 height=4
src="images/help/android/image001.gif"></p>

<p><span style='color:navy'> 方式二： 下载并使用 Cisco AnyConnect 3.1 版： <a
			href="downloads/anyconnect-win-3.1.00495-pre-deploy-k9.msi">anyconnect-win-3.1.00495-pre-deploy-k9.msi</a>
			使用说明同 Windows AnyConnect 一样。
</span></p>
<p><span > 具体步骤如下：</span></p>
<p><span style='color:navy'> 1: 安装 Cisco AnyConnect 3.1 版： <a
			href="downloads/anyconnect-win-3.1.00495-pre-deploy-k9.msi">anyconnect-win-3.1.00495-pre-deploy-k9.msi</a>
</span></p>
<p> <img border=0 width=901 height=501
src="images/help/blackberry/image201.jpg"></p>
<p><span style='color:navy'> 2:用户在"徽标 (图像)"上左键单击(在"“开始”菜单"中)， 用户在"Connect (按钮)"上左键单击(在"Cisco AnyConnect Secure Mobility Client"中), 填写服务器 14.49.0.36:444 或 64.71.190.198:444 然后点击设置（齿轮图标）
</span></p>
<p> <img border=0 width=401 height=201
src="images/help/blackberry/image204.jpg"></p>
<p><span style='color:navy'> 3:点击"Preferences"， 不选 “Block connections to untrusted servers”
</span></p>
<p> <img border=0 width=401 height=201
src="images/help/blackberry/image205.jpg"></p>
<p><span style='color:navy'> 4:输入你的用户名和密码（就是你在 www.iqlinkus.com 注册的电邮和密码）， 即可。
</span></p>
<p> <img border=0 width=901 height=501
src="images/help/blackberry/image206.jpg"></p>
<p><img border=0 width=576 height=4
src="images/help/android/image001.gif"></p>

<p><span style='color:navy'>方式伞:下载并使用 <a
			href="http://www.shrew.net/download/vpn"> Shrew-VPN （悍妇-VPN）</a>： <a
			href="http://www.shrew.net/download/vpn/vpn-client-2.2.0-rc-2.exe"> 最新版2.2.0-rc-2 </a>； 然后import Cisco VPN Client的pcf 设置文件。 此文件在C:\Program Files\Cisco Systems\VPN Client\Profiles中。
</span></p>

<p><span > 具体步骤如下：</span></p>
<p><span style='color:navy'> 1: 安装 Shrew-VPN （悍妇-VPN）</a>： <a
			href="http://www.shrew.net/download/vpn/vpn-client-2.2.0-rc-2.exe"> 最新版2.2.0-rc-2 </a>
</span></p>
<p><span style='color:navy'> 2: 运行 VPN Access Manager,
</span></p>
<p> <img border=0 width=401 height=201
src="images/help/blackberry/image301.jpg"></p>
<p><span style='color:navy'> 3: 下载 我们的（Cisco PCF File)  <a
			href="downloads/iqlink11.pcf">iqlink11.pcf</a>; 点击File， Import； 导入这个iqlink11.pcf 文件 
</span></p>
<p> <img border=0 width=401 height=201
src="images/help/blackberry/image302.jpg"></p>
<p><span style='color:navy'> 4: 点击 Connect，输入你的用户名和密码（就是你在 www.iqlinkus.com 注册的电邮和密码）， 即可。
</span></p>
<p> <img border=0 width=401 height=301
src="images/help/blackberry/image303.jpg"></p>
<p><img border=0 width=576 height=4
src="images/help/android/image001.gif"></p>

</div>
