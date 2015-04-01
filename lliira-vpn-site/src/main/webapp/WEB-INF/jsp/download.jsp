<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="下载客户端软件" section="download" />

<vpn:servers />

<h1>下载客户端软件</h1>

<p align="center"><font color="blue"> <b> 我们购买了Cisco 大量 AnyConnect牌照； 是其网络加速服务的制定提供商。 </b>  </font>	</p>
<p align="center" ><font color="blue"> <b> 所有的客户端软件，均Cisco为我们提供的原装正版软件； 未经任何修改，绝对不含病毒和Spyware， 请用户放心的下载和安装！</b>  </font>	</p>

<div class="download ui-widget-content ui-corner-all">
	<h3>AnyConnect客户端 3.0.3054 (最稳定，推荐安装）</h3>
	<ul>
		<li>Windows XP/Vista/7/8：<a
			href="downloads/anyconnect-win-3.0.3054-pre-deploy-k9.msi">anyconnect-win-3.0.3054-pre-deploy-k9.msi</a>
			(3.36M)
		</li>
		<li>Linux 32位系统：<a
			href="downloads/anyconnect-linux-3.0.3054-k9.pkg">anyconnect-linux-3.0.3054-k9.pkg</a>
			(8.52M)
		</li>
		<li>Linux 64位系统：<a
			href="downloads/anyconnect-linux-64-3.0.3054-k9.pkg">anyconnect-linux-64-3.0.3054-k9.pkg</a>
			(6.57M)
		</li>
		<li>Mac OS：<a
			href="downloads/anyconnect-macosx-i386-3.0.3054-k9.dmg">anyconnect-macosx-i386-3.0.3054-k9.dmg</a>
			(4.95M)
		</li>
	</ul>
	<div align="center">
		客户端软件的安装和使用指南请参阅<a href="<s:url action='help' />">帮助文档</a>。
	</div>
<!---		如果您在Windows下下载Linux的安装包，请使用右键点击并选择“另存为”，并将保存文件的扩展名改为.pkg，否则Windows会将.pkg类型默认为XML类型而导致无法正常下载。 --->
<p align="center"><font color="blue"> <b> （1） AnyConnect和Windows的Internet Connection Sharing（ICS)不兼容，如果你打开了ICS, 把它关掉才能正常使用AnyConnect。 （2） Anyconnect 和 Connectify 也不能一起工作，它们同时开启将导致网络不稳定。 </b> </font> </p>
</div>

<div class="download ui-widget-content ui-corner-all">
	<h3>VPN Client客户端</h3>
	<ul>
		<li>Windows XP/Vista/7/8 32位系统：<a
			href="downloads/vpnclient-win-msi-5.0.07.0410-k9.exe">vpnclient-win-msi-5.0.07.0410-k9.exe</a>
			(7.63M)
		</li>
		<li>Windows XP/Vista/7/8 64位系统：<a
			href="downloads/vpnclient-winx64-msi-5.0.07.0440-k9.exe">vpnclient-winx64-msi-5.0.07.0440-k9.exe</a>
			(4.79M)
		</li>
		<li>Linux：<a
			href="downloads/vpnclient-linux-x86_64-4.8.02.0030-k9.tar.gz">vpnclient-linux-x86_64-4.8.02.0030-k9.tar.gz</a>
			(1.95M)
		</li>
		<li>Mac OS：<a
			href="downloads/vpnclient-darwin-4.9.01.0280-universal-k9.dmg">vpnclient-darwin-4.9.01.0280-universal-k9.dmg</a>
			(14.6M)
		</li>
	</ul>
	<div align="center">
		客户端软件的安装和使用指南请参阅<a href="<s:url action='help' />">帮助文档</a>。
	</div>
</div>

<div class="download ui-widget-content ui-corner-all">
	<h3>AnyConnect客户端 3.1  </h3>
	<h4>（(不稳定，除非是Windows 8，不推荐安装）</h4>
	<ul>
		<li>Windows XP/Vista/7/8：<a
			href="downloads/anyconnect-win-3.1.00495-pre-deploy-k9.msi">anyconnect-win-3.1.00495-pre-deploy-k9.msi</a>
			(3.36M)
		</li>
		<li>Linux 32位系统：<a
			href="downloads/anyconnect-predeploy-linux-3.1.00495-k9.tar.gz">anyconnect-predeploy-linux-3.1.00495-k9.tar.gz</a>
			(8.52M)
		</li>
		<li>Linux 64位系统：<a
			href="downloads/anyconnect-predeploy-linux-64-3.1.00495-k9.tar.gz">anyconnect-predeploy-linux-64-3.1.00495-k9.tar.gz</a>
			(6.57M)
		</li>
		<li>Mac OS：<a
			href="downloads/anyconnect-macosx-i386-3.1.00495-k9.dmg">anyconnect-macosx-i386-3.1.00495-k9.dmg</a>
			(4.95M)
		</li>
	</ul>
	<p class="warning" align="center"><b> 设置中一定要不选中 “Block connections to untrusted servers”. </b></p>
	<p align="center"><b> 如果使用中得到如下错误提示“The service provider in your current location is restricting access to the Internet.” 
	You need to log on with the service provider before you can establish a VPN session. 
	You can try this by visiting any website with your browser.”， 那么请重启电脑，或改安装 AnyConnect 3.0.3054。 </b></p>
</div>

<vpn:footer />
