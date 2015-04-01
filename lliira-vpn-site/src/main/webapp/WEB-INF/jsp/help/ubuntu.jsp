
<div class=WordSection1>

	<p>
		<b><span style='font-size: 18.0pt; line-height: 115%;'>在</span></b><b><span
			style='font-size: 18.0pt; line-height: 115%'>Ubuntu</span></b><b><span
			style='font-size: 18.0pt; line-height: 115%;'>下使用</span></b><b><span
			style='font-size: 18.0pt; line-height: 115%'>VPN</span></b>
	</p>

	<p>
		<img width=575 height=3 src="images/help/ubuntu/image001.gif">
	</p>

	<p>
		<span>简介目前在</span>Ubuntu<span>下使用我们的</span>VPN<span>主要有以下几种方式，您可以选择适合您的方式进行连接，我们推</span>
	</p>

	<p>
		<span>荐您使用第一种方式，该方式安装和配置最为简单。</span>
	</p>

	<p>
		<span style='color: blue'>1. <a href="#method_1">使用VPNC&nbsp;Client连接VPN</a></span>
	</p>

	<p>
		<span style='color: blue'>2. <a href="#method_2">使用Cisco官方的Cisco&nbsp;AnyConnect</a></span>
	</p>

	<p>
		<img border=0 width=575 height=3 src="images/help/ubuntu/image001.gif">
	</p>

	<p>
       <a name="method_1">
		<span style='color: navy'>方法一：</span><span style='color: navy'>  
		</span><span style='color: blue'>使用</span><span style='color: blue'>VPNC&nbsp;Client</span><span
			style='color: blue'>连接</span><span style='color: blue'>VPN</span>
       </a><font color="red"> <b>（服务器请填写 216.218.196.158） </font> </b>
	</p>

	<p>&nbsp;</p>

	<p>
		1.&nbsp;<span>首先您需要安装「</span>VPNC<span>」，请在命令终端中运行以下命令安装</span>VPNC<span>：</span>
	</p>

	<p>                sudo&nbsp;apt-get&nbsp;install&nbsp;vpnc</p>

	<p>
		<span>您也可以自行从</span>VPNC<span>网站</span>&nbsp;&nbsp;&nbsp;(http://www.unix-ag.uni-kl.de/~massar/vpnc/)<span>上下载和安装。</span>
	</p>

	<p>&nbsp;</p>

	<p>
		2.&nbsp;<span>修改配置文件：</span>default.conf
	</p>

	<p>
		<span>使用您喜欢的文本编辑器打开配置文件</span>default.conf .  <span>例如：</span>
	</p>

	<p style='text-indent: .5in'>sudo&nbsp;vi&nbsp;/etc/vpnc/default.conf</p>

	<p>
		<span>并且修改以下内容：</span>
	</p>

	<p style='text-indent: .5in'>IPSec&nbsp;&nbsp;gateway&nbsp;&nbsp;iqlink1.us</p>

	<p style='margin-left: .5in'>IPSec&nbsp;&nbsp;ID&nbsp;&nbsp;iqlink</p>

	<p style='text-indent: .5in'>IPSec&nbsp;&nbsp;secret&nbsp;&nbsp;iqlink</p>

	<p>
		#<span>你在</span>iqlinkvpn<span>的帐号（红色部分）</span>
	</p>

	<p style='text-indent: .5in'>
		Xauth&nbsp;&nbsp;username&nbsp;&nbsp;<span style='color: red'><a
			href="mailto:user@email.com"><span style='color: red'>user@email.com</span></a></span>
	</p>

	<p>
		#<span>你在</span>iqlinkvpn<span>的密码（红色部分）</span>
	</p>

	<p style='text-indent: .5in'>
		Xauth&nbsp;&nbsp;password&nbsp;&nbsp;<span style='color: red'>mypassword</span>
	</p>

	<p>&nbsp;</p>

	<p>
		<span>请注意其中</span>IPSec gateway<span>指的是服务器的域名或者</span>IP<span>地址，您可以使用</span>iqlink1.us,
		iqlink2.us,
		iqlink3.us,&nbsp;&nbsp;&nbsp;iqlink4.us,&nbsp;&nbsp;&nbsp;iqlink5.us<span>等服务器（请注意您是否有权限访问这些服务器）。</span>
	</p>

	<p>
		3.&nbsp;<span>连接</span>VPN
	</p>

	<p>
		<span>在命令终端中，使用以下命令启动</span>VPNC<span>进行连接</span>
	</p>

	<p style='text-indent: .5in'>sudo&nbsp;&nbsp;vpnc&nbsp;&nbsp;/etc/vpnc/default.conf</p>

	<p>
		    
		Enter&nbsp;&nbsp;&nbsp;IPSec&nbsp;&nbsp;&nbsp;secret&nbsp;&nbsp;&nbsp;for&nbsp;&nbsp;&nbsp;GroupName:&nbsp;&nbsp;&nbsp;<span>请输入我们提供的组密码</span>,&nbsp;&nbsp;&nbsp;<span>组密码</span>:&nbsp;&nbsp;&nbsp;iqlink
	</p>

	<p>
		     Enter&nbsp;&nbsp;&nbsp;loginname:&nbsp;&nbsp;&nbsp;<span>请输入您的账户名</span>
	</p>

	<p>
		    
		Enter&nbsp;&nbsp;&nbsp;password&nbsp;&nbsp;&nbsp;for&nbsp;&nbsp;&nbsp;loginname:&nbsp;&nbsp;&nbsp;<span>请输入您的密码</span>
	</p>

	<p>
		4.&nbsp;<span>断开</span>VPN<span>连接</span>
	</p>

	<p>
		<span>执行以下命令可以断开</span>VPNC<span>连接</span>:
	</p>

	<p style='text-indent: .5in'>sudo&nbsp;&nbsp;/usr/sbin/vpnc-disconnect</p>

	<p>
		<img border=0 width=575 height=3 src="images/help/ubuntu/image001.gif">
	</p>

	<p>
       <a name="method_2">
		<span style='color: navy'>方法二：</span><span style='color: navy'>  
		</span><span style='color: navy'>使用官方的</span><span style='color: navy'>Cisco&nbsp;AnyConnect</span><span
			style='color: navy'>连接</span>
       </a><font color="red"> <b>（服务器请填写 64.71.190.198:444） </font> </b>
	</p>

	<p>
		<span style='color: teal'>1.&nbsp;</span><span style='color: teal'>安装</span><span
			style='color: teal'>Cisco&nbsp;AnyConnect</span>
	</p>

	<p>
		<span>安装</span>Cisco&nbsp;AnyConnect<span>. </span><span>一共有两种方式</span><span>.
		</span><span>一种是自动安装，同时如果您不想安装</span>Java<span>插件您也可以</span><span>
		</span><span>自己手动下载和安装</span>AnyConnect<span>。下面我们分别介绍这两种方式：</span>
	</p>

	<p>
		1.a.<span>自动安装</span>
	</p>

	<p>
		<span>安装</span>Java<span>插件</span>:  <span>使用</span>Cisco&nbsp;AnyConnect<span>要求您的机器预先安装了</span>Java&nbsp;Plug-in<span>，如果您的机器没有安</span>Java
		Plug-in<span>请使用以下命令进行安装：</span>
	</p>

	<p style='text-indent: .5in'>sudo&nbsp;&nbsp;apt-get&nbsp;&nbsp;install&nbsp;&nbsp;openjdk-6-jre&nbsp;&nbsp;icedtea6-plugin</p>

	<p>
		<span>请在</span>Firefox<span>中输入</span>about:plugins<span>，并确认</span>Java&nbsp;plugins<span>已经正确安装后再继续后面的步骤。</span>
	</p>

	<p>
		b&nbsp;&nbsp;<span>在</span>Firefox&nbsp;<span>打开网址</span><a
			href="https://iqlink1.us:444/">https://iqlink1.us:444</a>
	</p>

	<p>
		<span>我们的系统将会自动为您安装</span>AnyConnect&nbsp;Secure&nbsp;Mobility&nbsp;Client<span>，在安装过程中需要管理员权限。在要求输入密码的时候请输入密码。</span>
	</p>

	<p>&nbsp;</p>

	<p>&nbsp;</p>

	<p>
		1.b.<span>手动安装</span>Cisco&nbsp;AnyConnect
	</p>

	<p>
		<span>要进行手动安装</span>Cisco&nbsp;AnyConnect<span>，首先您要先下载</span>Java<span>的运行环境，在</span>Ubuntu<span>的软件中心搜索</span>&nbsp;Java<span>安装</span>&nbsp;OpenJDK&nbsp;Java&nbsp;7
		Runtime<span>，</span>IcedTea&nbsp;Java&nbsp;Web&nbsp;Start,&nbsp;Icedtea&nbsp;Java&nbsp;<span>插件</span>
	</p>

	<p>
		<img border=0 width=555 height=431
			src="images/help/ubuntu/image002.gif">
	</p>

	<p>&nbsp;</p>

	<p>
		<span>接下来请您先下载最新版的</span>Cisco&nbsp;AnyConnect<span>到您本地机器。下面是下载连接：</span>
	</p>

	<p>
		<span>·</span>&nbsp;&nbsp;64<span>位版本的</span>Cisco&nbsp;AnyConnect&nbsp;3.0.07059
		(http://www.iqlinkvpn.com/downloads/anyconnect-Linux_64-2.5.2019-k9.tar)
	</p>

	<p>
		<span>·</span>&nbsp;&nbsp;32<span>位版本的</span>Cisco&nbsp;AnyConnect&nbsp;3.0.5080
		(&nbsp;http://www.iqlinkvpn.com/downloads/anyconnect-linux-2.5.2019-k9.tar)
	</p>

	<p>
		<span>下载完成后您首先需要打开一个终端，将文件解压缩（以下代码中使用</span>64<span>位版本为例子，</span><span>
		</span><span>如果您使用</span>32<span>位版，您需要更改相应的文件名）</span>
	</p>

	<p style='text-indent: .5in'>tar&nbsp;&nbsp;xvfz&nbsp;&nbsp;anyconnect-predeploy-linux-64-3.0.07059-k9.tar.gz</p>

	<p>
		<span>解压缩后运行以下命令进行安装：</span>
	</p>

	<p style='text-indent: .5in'>cd&nbsp;&nbsp;anyconnect-3.0.07059/vpn</p>

	<p style='text-indent: .5in'>sudo&nbsp;&nbsp;vpn_install.sh</p>

	<p>
		<span>如果终端提示“</span>Do&nbsp;&nbsp;&nbsp;you&nbsp;&nbsp;&nbsp;accept&nbsp;&nbsp;&nbsp;the&nbsp;&nbsp;&nbsp;terms&nbsp;&nbsp;&nbsp;in&nbsp;&nbsp;&nbsp;the&nbsp;&nbsp;&nbsp;license&nbsp;&nbsp;&nbsp;agreement?<span>”，请输入</span>&nbsp;&nbsp;&nbsp;Y   
		<span>安装完成以后您可以运行以下命令来判断</span>Agent<span>是否已经正常启动：</span>
	</p>

	<p style='text-indent: .5in'>ps&nbsp;&nbsp;aux&nbsp;&nbsp;|&nbsp;&nbsp;grep&nbsp;&nbsp;cisco</p>

	<p>
		<span>如果有</span>vpnagentd<span>在运行表示安装已经完成。</span>
	</p>

	<p>
		<span>安装完成以后我们建议您重新登录一下您的桌面以保证快捷方式正常显示。然后您就可以继续下</span><span>
		</span><span>面的步骤了。</span>
	</p>

	<p>
		<span style='color: teal'>2.&nbsp;</span><span style='color: teal'>连接</span><span
			style='color: teal'>&nbsp;Anyconnect&nbsp;VPN</span>
	</p>

	<p>
		<span>在</span>&nbsp;Ubuntu&nbsp;12.04&nbsp;<span>之前，点击</span>&nbsp;Internet&nbsp;<span>菜单的</span>&nbsp;Cisco&nbsp;AnyConnect&nbsp;Secure&nbsp;Mobility&nbsp;Client
	</p>

	<p>
		<img border=0 width=576 height=218
			src="images/help/ubuntu/image003.jpg">
	</p>

	<p>
		<span>在</span>&nbsp;Ubuntu&nbsp;12.04&nbsp;<span>开打</span>&nbsp;Dash<span>主页，搜索</span>&nbsp;Cisco<span>，打开</span>&nbsp;Cisco&nbsp;AnyConnect&nbsp;Secure&nbsp;Mobility&nbsp;Client
	</p>

	<p>
		<img border=0 width=576 height=326
			src="images/help/ubuntu/image004.gif">
	</p>

	<p>&nbsp;</p>

	<p>
		<span>在</span>Connect&nbsp;to<span>中输入您要连接的服务器地址，如</span>iqlink1.us,&nbsp;iqlink2.us,&nbsp;iqlink3.us,&nbsp;iqlink4.us,
		iqlink5.us<span>等（请确定您有访问权限）。点击右侧的配置按钮，启用本地网络访问</span>“Enable&nbsp;local
		LAN&nbsp;access&nbsp;(if&nbsp;conﬁgured)”
	</p>

  <p>可用的服务器及端口信息请参看网站提供的通知。如域名无法连接，请用服务器的IP地址. 注意： AnyConnect的端口现在是444，请加 :444　如图所示</p>
  
	<p>
		<img border=0 width=260 height=276
			src="images/help/ubuntu/image005.jpg">
	</p>

	<p>
		<span>若弹出的对话框，</span><span> </span><span>选择</span>Accep.   <span>然后输入您的账户名和密码（您在我们网站注册时所使用的用户名和密码），然后点击连接。</span>
	</p>

	<p>
		<img border=0 width=294 height=314
			src="images/help/ubuntu/image006.jpg">
	</p>

	<p>
		<span>一旦</span>AnyConnect<span>连接上我们的服务器，那么将在您系统任务栏上显示这个图标：</span>
	</p>

	<p>
		<img border=0 width=311 height=23
			src="images/help/ubuntu/image007.gif">
	</p>

	<p>&nbsp;</p>

	<p>
		<span style='color: teal'>3.&nbsp;</span><span style='color: teal'>断开</span><span
			style='color: teal'>AnyConnect&nbsp;VPN</span>
	</p>

	<p>
		<span>要断开</span>AnyConnect<span>连接，请点击任务栏上的</span>AnyConnect<span>图标并且点击</span>Disconnect<span>。</span>
	</p>

	<p>&nbsp;</p>

	<p>&nbsp;</p>

	<p>
		<img border=0 width=281 height=302
			src="images/help/ubuntu/image008.gif">
	</p>

</div>
