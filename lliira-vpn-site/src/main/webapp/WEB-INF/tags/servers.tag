<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="server-info ui-widget-content ui-corner-all">
	<h3 align="center"> 重要通知. ！必读！ </h3>
	
  <p>（1） <span class="warning"> 请一定要使用服务器IP地址连接，否则你连不上服务器 </span>。 </p>
  
  <s:if test="user != null">
	  <ul>
	    <c:forEach items="${servers}" var="server">
	      <li><span class="warning">${server.ip}</span> （${server.description}）</li>
	    </c:forEach>
	  </ul>
	</s:if>
	<s:else>
	  <p class="warning">请登录后查看VPN服务器地址。付费用户有权使用更多的VPN服务器。</p>
	</s:else>
	
	  
	  <p>（2） AnyConnect连接的端口号是 <span class="warning">444</span>， 请在服务器IP地址后加:444（比如你想连iqlink1.us，那么连接地址是 <span class="warning">64.71.190.198:444
			</span>）。 对于使用Cisco VPN Client 或系统内置的用户，你的使用方式没有改变。 
			</p>
		  <p><img border=0 width=576 height=4
src="images/help/android/image001.gif"></p>
			<p>（5） <font color="black"> <b>  我们保护个人隐私， 绝不泄露用户的电邮，绝不向用户的电邮发广告或垃圾邮件；我们只记录用户的登录和流量。</b>  </font>	</p>	
			<p>（6） <font color="black"> <b>  请遵守国家相关法规，禁止使用我们的服务器进行入侵，破坏，下载受版权保护的音像软件等违法行为； 否则我们立刻冻结帐号，并记录相关登陆数据，向有关司法部门提供证据。</b>  </font>	</p>
		  <p><img border=0 width=576 height=4
src="images/help/android/image001.gif"></p>				
		    <p>（7）  <font color="blue"><b>速度和流量是大家最关心的。 关于速度：请大家使用 
	 <a href="http://www.speedtest.net">http://www.speedtest.net</a> 测测你登上服务器后的速度； 流畅地观看YouTube，你需要0.6Mbps （每秒0.6兆比特）的下载速度。 关于流量： 我们的流量计算是基于ASA服务器的汇报，非常准确。另外你实际使用的流量也许不向你想像的那么多，比如50GB/月等同于（50000×8）/（0.6×3600）=180小时的YouTube，能每天看6小时YouTube。 </font> </b> </p>				  
 <p>（8）  <font color="navy"><b> 激活电邮在你注册后自动地发出； 如未收到，请到垃圾信箱中查找。 请勿使用 qq.com ，sina.com， foxmail.com 的电邮注册，否则你收不到激活电邮； 因为他们屏蔽我们的电邮； 我们不再转发打回来的激活电邮了。 </b>  </font> </p>		
 <p>(9）  <font color="navy"><b>由于我们网站(www.iqlinkus.com) 的域名在中国地区被人为的污染了，大家请使用新域名访问我们的网站： <a href="http://www.iqlinkus.net"> http://www.iqlinkus.net</a> </b></font></p>		  
<p>(10）  <font color="red"><b> 北京时间（4月9日中午）， 我们服务器的IP地址在中国地区被封了。  北京时间（4月11日早上10点）， 新服务器的IP地址已经发给了付费用户，付费用户请查电邮。 
 </b></font></p>
 <p>(11）  <font color="red"><b>
由于163.com 和126.com的电邮被打回，许多付费用户没有收到新服务器地址的电邮。 请登录后查看我们的VPN服务器地址。 大部分服务器可用AnyConnect方式（加:444)，也可以用其它一切VPN方式。 我们的所有服务器的IP地址已经更换完了，现在正在做服务器列表，让免费的用户和付费的用户能看到不同的列表。 
 </b></font></p> 
</div>

