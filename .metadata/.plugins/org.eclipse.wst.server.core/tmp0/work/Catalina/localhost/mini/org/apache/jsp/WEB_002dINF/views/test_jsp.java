/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.91
 * Generated at: 2024-09-05 06:24:10 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class test_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/layout/header.jspf", Long.valueOf(1725502612369L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1724403605343L));
    _jspx_dependants.put("jar:file:/C:/Users/ICT01-21/Desktop/miniProject%2009-03/miniProject%2009-03/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/miniProject2/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/layout/footer.jspf", Long.valueOf(1725347375260L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>DANDI</title>\r\n");
      out.write("\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<!-- 부트스트랩 -->\r\n");
      out.write("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("<!-- 슬릭슬라이더 -->\r\n");
      out.write("<!-- 제이쿼리 -->\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>\r\n");
      out.write("<!--slick slider 필요 태그 st-->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"//cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.8/slick-theme.min.css\">\r\n");
      out.write("<!-- jQuery CDN -->\r\n");
      out.write("<script src=\"//code.jquery.com/jquery-1.11.0.min.js\"></script>\r\n");
      out.write("<script src=\"//code.jquery.com/jquery-migrate-1.2.1.min.js\"></script>\r\n");
      out.write("<!-- slick Carousel CDN -->\r\n");
      out.write("<script type=\"text/javascript\" src=\"//cdn.jsdelivr.net/jquery.slick/1.5.7/slick.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!--폰트-->\r\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n");
      out.write("<link href=\"https://fonts.googleapis.com/css2?family=Gothic+A1:wght@100;200;300;400;500;600;700;800;900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n");
      out.write("<link href=\"https://fonts.googleapis.com/css2?family=Gothic+A1:wght@100;200;300;400;500;600;700;800;900&family=Noto+Sans:ital,wght@0,100..900;1,100..900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/mini/css/header.css\" type=\"text/css\"/>\r\n");
      out.write("<script>\r\n");
      out.write("   $(document).ready(function(){\r\n");
      out.write("         $('.autoplay').slick({\r\n");
      out.write("            centerMode: true,\r\n");
      out.write("            slidesToShow: 4,\r\n");
      out.write("            slidesToScroll: 1,\r\n");
      out.write("            autoplay: true,\r\n");
      out.write("            autoplaySpeed: 2000,\r\n");
      out.write("           \r\n");
      out.write("            draggable:true ,\r\n");
      out.write("            infinite: true\r\n");
      out.write("         });\r\n");
      out.write("      \r\n");
      out.write("   });\r\n");
      out.write("   $(document).ready(function(){\r\n");
      out.write("      $('.center').slick({\r\n");
      out.write("         centerMode: true,\r\n");
      out.write("         centerPadding: '60px',\r\n");
      out.write("         slidesToShow: 5,\r\n");
      out.write("         autoplay: true,\r\n");
      out.write("         autoplaySpeed: 2000,\r\n");
      out.write("       \r\n");
      out.write("         draggable:true ,\r\n");
      out.write("         infinite: true,\r\n");
      out.write("         responsive: [\r\n");
      out.write("         {\r\n");
      out.write("            breakpoint: 768,\r\n");
      out.write("            settings: {\r\n");
      out.write("            arrows: false,\r\n");
      out.write("            centerMode: true,\r\n");
      out.write("            centerPadding: '40px',\r\n");
      out.write("            slidesToShow: 3\r\n");
      out.write("            }\r\n");
      out.write("         },\r\n");
      out.write("         {\r\n");
      out.write("            breakpoint: 480,\r\n");
      out.write("            settings: {\r\n");
      out.write("            arrows: false,\r\n");
      out.write("            centerMode: true,\r\n");
      out.write("            centerPadding: '40px',\r\n");
      out.write("            slidesToShow: 1\r\n");
      out.write("            }\r\n");
      out.write("         }\r\n");
      out.write("      ]\r\n");
      out.write("      });\r\n");
      out.write("   });\r\n");
      out.write("</script>\r\n");
      out.write("<script>\r\n");
      out.write("   $(function(){\r\n");
      out.write("          // 스크롤 시 header fade-in\r\n");
      out.write("          $(document).on('scroll', function(){\r\n");
      out.write("              if($(window).scrollTop() > 100){\r\n");
      out.write("                  $(\"#header\").removeClass(\"deactive\");\r\n");
      out.write("                  $(\"#header\").addClass(\"active\");\r\n");
      out.write("              }else{\r\n");
      out.write("                  $(\"#header\").removeClass(\"active\");\r\n");
      out.write("                  $(\"#header\").addClass(\"deactive\");\r\n");
      out.write("              }\r\n");
      out.write("          })\r\n");
      out.write("      \r\n");
      out.write("      });\r\n");
      out.write("   function openLoginPopup() {\r\n");
      out.write("       window.open('/mini/mypage/login', 'LoginPopup', 'width=400, height=550 ,left=800, top=100');\r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("	function joinPopup(){\r\n");
      out.write("		 window.open('/mini/mypage/join', 'JoinPopup', 'width=600, height=850 ,left=800, top=100');\r\n");
      out.write("	}\r\n");
      out.write("\r\n");
      out.write("	function loginClosed() {\r\n");
      out.write("	    // 로그인 처리 로직\r\n");
      out.write("	    // 예를 들어, resultVo를 통해 사용자 정보를 가져왔다고 가정합니다.\r\n");
      out.write("	    const userId = resultVo.getUserid();\r\n");
      out.write("	    const userName = resultVo.getUsername();\r\n");
      out.write("	    \r\n");
      out.write("	    // 세션에 로그인 상태 저장 (예시로 localStorage 사용)\r\n");
      out.write("	    session.setAttribute(\"logId\", resultVo.getUserid());\r\n");
      out.write("		session.setAttribute(\"logName\", resultVo.getUsername());\r\n");
      out.write("		session.setAttribute(\"logStatus\", \"Y\");\r\n");
      out.write("		\r\n");
      out.write("\r\n");
      out.write("	    // 메인 페이지의 로컬 스토리지 업데이트 및 로그인 상태 갱신\r\n");
      out.write("	    if (window.opener && !window.opener.closed) {\r\n");
      out.write("	        window.opener.updateLoginStatus(userId, userName);\r\n");
      out.write("	    }\r\n");
      out.write("\r\n");
      out.write("	    // 팝업 창 닫기\r\n");
      out.write("	    window.close();\r\n");
      out.write("	}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("   <header>\r\n");
      out.write("     ");
      out.write("\r\n");
      out.write("   </header>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("	let max_temp = -100;\r\n");
      out.write("	let min_temp = 100;\r\n");
      out.write("	let max_min_temp = [];\r\n");
      out.write("	let rain = [];\r\n");
      out.write("    var labels = {};\r\n");
      out.write("    var datas = {};\r\n");
      out.write("    var chart;\r\n");
      out.write("    var currentLabels = []; // 현재 선택된 날짜의 시간 데이터\r\n");
      out.write("    var currentData = []; // 현재 선택된 날짜의 온도 데이터\r\n");
      out.write("    var selectedDate = \"\"; // 현재 선택된 날짜를 저장하는 변수\r\n");
      out.write("    var weather = {};\r\n");
      out.write("    var weatherlist = {};\r\n");
      out.write("    let max_min_tempData = {}; // 각 날짜의 최고/최저 온도와 날씨 아이콘을 저장\r\n");
      out.write("    function updateChart(date) {\r\n");
      out.write("        selectedDate = date;  // 선택된 날짜를 저장\r\n");
      out.write("        currentLabels = labels[selectedDate];\r\n");
      out.write("        currentData = datas[selectedDate];\r\n");
      out.write("\r\n");
      out.write("        // 오늘 날짜인지 확인\r\n");
      out.write("        let today = new Date();\r\n");
      out.write("        let todayFormatted = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);\r\n");
      out.write("\r\n");
      out.write("        // 오늘 날짜이면 closestIndex부터 데이터 슬라이스\r\n");
      out.write("        if (selectedDate === todayFormatted) {\r\n");
      out.write("            let closestIndex = 0;\r\n");
      out.write("            let currentHour = today.getHours();\r\n");
      out.write("            let minDifference = Number.MAX_VALUE;\r\n");
      out.write("\r\n");
      out.write("            for (let i = 0; i < labels[selectedDate].length; i++) {\r\n");
      out.write("                let hour = parseInt(labels[selectedDate][i].substring(0, 2)); // 시간 추출\r\n");
      out.write("                let diff = Math.abs(currentHour - hour); // 현재 시간과 각 시간의 차이 계산\r\n");
      out.write("\r\n");
      out.write("                if (diff < minDifference) {\r\n");
      out.write("                    minDifference = diff;\r\n");
      out.write("                    closestIndex = i;  // 가장 가까운 시간의 인덱스 저장\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("            // 오늘의 가까운 시간대부터 차트 업데이트\r\n");
      out.write("            chart.data.labels = currentLabels.slice(closestIndex);  // 가장 가까운 시간부터 시작\r\n");
      out.write("            chart.data.datasets[0].data = currentData.slice(closestIndex);  // 가장 가까운 시간대의 데이터부터 시작\r\n");
      out.write("            console.log(\"오늘 날짜 차트 업데이트:\", chart.data.labels);\r\n");
      out.write("        } else {\r\n");
      out.write("            // 오늘이 아닌 경우 전체 데이터를 보여줌\r\n");
      out.write("            chart.data.labels = currentLabels;\r\n");
      out.write("            chart.data.datasets[0].data = currentData;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        chart.update({\r\n");
      out.write("            duration: 800,  // 0.8초 동안 애니메이션 효과\r\n");
      out.write("            easing: 'easeInOutQuad' // 애니메이션 속도 조절\r\n");
      out.write("        });\r\n");
      out.write("    }\r\n");
      out.write("    window.onload = function () {\r\n");
      out.write("        setTimeout(function () {\r\n");
      out.write("            var xHttp = new XMLHttpRequest();\r\n");
      out.write("\r\n");
      out.write("            xHttp.onreadystatechange = function () {\r\n");
      out.write("                if (this.readyState == 4 && this.status == 200) {\r\n");
      out.write("                    let result = JSON.parse(this.responseText);\r\n");
      out.write("                    \r\n");
      out.write("                    weather = result;\r\n");
      out.write("                    console.log(\"값오는지 확인\", weather);\r\n");
      out.write("                   \r\n");
      out.write("                    max_min_temp = [];\r\n");
      out.write("                    rain = [];\r\n");
      out.write("                   \r\n");
      out.write("\r\n");
      out.write("                    result.list.forEach(item => {\r\n");
      out.write("                        let date = new Date(item.dt_txt);\r\n");
      out.write("                        let formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);\r\n");
      out.write("                        let formattedTime = ('0' + date.getHours()).slice(-2) + ':' + ('0' + date.getMinutes()).slice(-2);\r\n");
      out.write("\r\n");
      out.write("                        if (!labels[formattedDate]) {\r\n");
      out.write("                            labels[formattedDate] = [];\r\n");
      out.write("                            datas[formattedDate] = [];\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        labels[formattedDate].push(formattedTime);  // 시간 추가\r\n");
      out.write("                        datas[formattedDate].push(item.main.temp);  // 온도 추가\r\n");
      out.write("\r\n");
      out.write("                        // 최고/최저 온도와 비 여부 저장\r\n");
      out.write("                        if (!max_min_tempData[formattedDate]) {\r\n");
      out.write("                            max_min_tempData[formattedDate] = { \r\n");
      out.write("                                max: -Infinity, \r\n");
      out.write("                                min: Infinity, \r\n");
      out.write("                                icon: item.weather[0].icon, \r\n");
      out.write("                                hasRain: false \r\n");
      out.write("                            };\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        if (item.main.temp_max > max_min_tempData[formattedDate].max) {\r\n");
      out.write("                            max_min_tempData[formattedDate].max = item.main.temp_max;\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        if (item.main.temp_min < max_min_tempData[formattedDate].min) {\r\n");
      out.write("                            max_min_tempData[formattedDate].min = item.main.temp_min;\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        // 비오는 날씨 아이콘 저장\r\n");
      out.write("                        if (item.weather[0].icon.startsWith(\"09\") || item.weather[0].icon.startsWith(\"10\")) {\r\n");
      out.write("                            max_min_tempData[formattedDate].hasRain = true;\r\n");
      out.write("                            max_min_tempData[formattedDate].icon = \"09n\"; // 비오는 이미지로 설정\r\n");
      out.write("                        }\r\n");
      out.write("                    });\r\n");
      out.write("\r\n");
      out.write("                    console.log(\"최고/최저 온도 및 비오는 날씨 저장:\", max_min_tempData);\r\n");
      out.write("\r\n");
      out.write("                    // 오늘 날짜 처리\r\n");
      out.write("                    let today = new Date();\r\n");
      out.write("                    let todayFormatted = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);\r\n");
      out.write("\r\n");
      out.write("                    if (!labels[todayFormatted]) {\r\n");
      out.write("                        todayFormatted = Object.keys(labels)[0]; // 첫 번째 날짜 선택\r\n");
      out.write("                    }\r\n");
      out.write("\r\n");
      out.write("                    currentLabels = labels[todayFormatted];\r\n");
      out.write("                    currentData = datas[todayFormatted];\r\n");
      out.write("                    selectedDate = todayFormatted; // 선택된 날짜 저장\r\n");
      out.write("\r\n");
      out.write("                    // 차트 초기화\r\n");
      out.write("                 const data = {\r\n");
      out.write("					    labels: currentLabels, \r\n");
      out.write("					    datasets: [{\r\n");
      out.write("					        data: currentData, \r\n");
      out.write("					        fill: true,\r\n");
      out.write("					        borderColor: \"#FFD632\",\r\n");
      out.write("					        backgroundColor:\"#FFF5CC\",\r\n");
      out.write("					        tension: 0.5,\r\n");
      out.write("					    }]\r\n");
      out.write("					};\r\n");
      out.write("					\r\n");
      out.write("					const config = {\r\n");
      out.write("					    type: \"line\",\r\n");
      out.write("					    data: data,\r\n");
      out.write("					    options: {\r\n");
      out.write("					        onClick: (event) => {\r\n");
      out.write("					            const points = chart.getElementsAtEventForMode(event, 'nearest', { intersect: true }, false);\r\n");
      out.write("					            if (points.length > 0) {\r\n");
      out.write("					                const elementIndex = points[0].index;\r\n");
      out.write("					                weatherlist = weather.list[elementIndex];\r\n");
      out.write("					                const timeLabel = data.labels[elementIndex];\r\n");
      out.write("					                const value = data.datasets[0].data[elementIndex];\r\n");
      out.write("					\r\n");
      out.write("					                nowweather(selectedDate, timeLabel, value, weatherlist);\r\n");
      out.write("					            }\r\n");
      out.write("					        },\r\n");
      out.write("					        scales: {\r\n");
      out.write("					            x: {\r\n");
      out.write("					                grid: { display: false }\r\n");
      out.write("					            },\r\n");
      out.write("					            y: {\r\n");
      out.write("					            	  suggestedMin: Math.min(...currentData) - 1, \r\n");
      out.write("					            	    suggestedMax: Math.max(...currentData) + 1,\r\n");
      out.write("					                beginAtZero: false,\r\n");
      out.write("					                grid: { display: false },\r\n");
      out.write("					                ticks: { display: false }  // Y축 값 숨기기\r\n");
      out.write("					            }\r\n");
      out.write("					        },\r\n");
      out.write("					        plugins: {\r\n");
      out.write("					            legend: { display: false },\r\n");
      out.write("					            datalabels: {\r\n");
      out.write("					                display: true,  // 각 포인트에 값을 표시\r\n");
      out.write("					                align: 'top',   // 점 위에 표시\r\n");
      out.write("					                anchor: 'end',  // 점의 끝 부분에 맞춤\r\n");
      out.write("					                color: 'black', // 텍스트 색상\r\n");
      out.write("					                formatter: function(value) {\r\n");
      out.write("					                    return value.toFixed(1);  // 소수점 2자리로 표시\r\n");
      out.write("					                }\r\n");
      out.write("					            }\r\n");
      out.write("					        }\r\n");
      out.write("					    },\r\n");
      out.write("					    plugins: [ChartDataLabels]  // datalabels 플러그인 활성화\r\n");
      out.write("					};\r\n");
      out.write("					\r\n");
      out.write("					chart = new Chart(document.getElementById(\"myChart\"), config);\r\n");
      out.write("                    // 차트에서 현재 시간에 가장 가까운 데이터로 시작하도록 설정\r\n");
      out.write("                    chart.update();\r\n");
      out.write("					\r\n");
      out.write("                    createDateButtons();\r\n");
      out.write("\r\n");
      out.write("                    // 오늘 날짜의 날씨 정보 표시\r\n");
      out.write("                    var nowday = new Date(selectedDate);\r\n");
      out.write("                    var days = ['일', '월', '화', '수', '목', '금', '토'];\r\n");
      out.write("                    var dayOfWeek = days[nowday.getDay()];\r\n");
      out.write("                    var tag = \"<div><img src='/mini/images/weather/\" + weather.list[0].weather[0].icon + \".png'/>\";\r\n");
      out.write("                    tag +=\"<span>\"+weather.list[0].main.temp+\"<span>°C</div>\";\r\n");
      out.write("                    tag +=\"<div><div class='nowdata'>강수확률:\"+weather.list[0].pop+\"%</div><div class='nowdata'>습도:\"+weather.list[0].main.humidity+\"%</div>\";\r\n");
      out.write("                    tag +=\"<div>풍속:\"+weather.list[0].wind.speed+\"m/s</div></div>\";\r\n");
      out.write("                    tag +=\"<div><div>날씨</div><div>\"+dayOfWeek+\"요일</div><div>\"+weather.list[0].weather[0].description+\"</div>\"\r\n");
      out.write("                    $(\"#nowweather\").html(tag);\r\n");
      out.write("                }\r\n");
      out.write("            };\r\n");
      out.write("\r\n");
      out.write("            var url = \"https://api.openweathermap.org/data/2.5/forecast?q=busan&appid=79908538f557fa6efd9c4f4b21907bca&lang=kr&units=metric\";\r\n");
      out.write("            xHttp.open(\"GET\", url, true);\r\n");
      out.write("            xHttp.send();\r\n");
      out.write("        }, 100);\r\n");
      out.write("    };\r\n");
      out.write("\r\n");
      out.write("    function createDateButtons() {\r\n");
      out.write("    	var nowday = new Date(selectedDate);\r\n");
      out.write("        var days = ['일', '월', '화', '수', '목', '금', '토'];\r\n");
      out.write("        var dayOfWeek = days[nowday.getDay()];\r\n");
      out.write("    	console.log(dayOfWeek);\r\n");
      out.write("        let buttonContainer = document.getElementById(\"buttonContainer\");\r\n");
      out.write("        buttonContainer.innerHTML = \"\"; // 중복 방지\r\n");
      out.write("        for (let date in labels) {\r\n");
      out.write("            let button = document.createElement(\"button\");\r\n");
      out.write("            button.innerHTML = \"<div><div>\"+dayOfWeek+\"</div><div><img src='/mini/images/weather/\"+max_min_tempData[date].icon+\".png'/></div><div>\"+ Math.round(max_min_tempData[date].max)+\"°C/\"+ Math.round(max_min_tempData[date].min)+\"°C</div></div>\";\r\n");
      out.write("            button.onclick = function () {\r\n");
      out.write("                updateChart(date);\r\n");
      out.write("                var a=max_min_tempData[date]\r\n");
      out.write("                updatenowweather(date,max_min_tempData)\r\n");
      out.write("                 // 버튼을 클릭하면 해당 날짜의 최고/최저 온도 및 날씨 확인\r\n");
      out.write("            };\r\n");
      out.write("            buttonContainer.appendChild(button);\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function nowweather(selectedDate,timeLabel,value,weatherlist){\r\n");
      out.write("\r\n");
      out.write("        var nowday = new Date(selectedDate);\r\n");
      out.write("        var days = ['일', '월', '화', '수', '목', '금', '토'];\r\n");
      out.write("        var dayOfWeek = days[nowday.getDay()];\r\n");
      out.write("        var tag=\"<div><img src='/mini/images/weather/\"+weatherlist.weather[0].icon+\".png'/>\";\r\n");
      out.write("        tag +=\"<span>\"+value+\"<span>°C</div>\";\r\n");
      out.write("        tag +=\"<div><div class='nowdata'>강수확률:\"+weatherlist.pop+\"%</div><div class='nowdata'>습도:\"+weatherlist.main.humidity+\"%</div>\";\r\n");
      out.write("        tag +=\"<div>풍속:\"+weatherlist.wind.speed+\"m/s</div></div>\";\r\n");
      out.write("        tag +=\"<div><div>날씨</div><div>\"+dayOfWeek+\"요일</div><div>\"+weatherlist.weather[0].description+\"</div>\"\r\n");
      out.write("        $(\"#nowweather\").html(tag);\r\n");
      out.write("    }\r\n");
      out.write("    function updatenowweather(date,max_min_tempData){\r\n");
      out.write("    	\r\n");
      out.write("    	test=date+\" 00:00\";\r\n");
      out.write("		\r\n");
      out.write("    	console.log(weather.list.length);\r\n");
      out.write("    	console.log(\"값이 넘어와야한다\",max_min_tempData[date].icon);\r\n");
      out.write("    	for(var i =0; i<weather.list.length;i++){\r\n");
      out.write("    		if(test==weather.list[i].dt_txt.substring(0,16)){\r\n");
      out.write("    		console.log(\"asdf\",date);\r\n");
      out.write("    		var nowday = new Date(date);\r\n");
      out.write("    		var days=['일', '월', '화', '수', '목', '금', '토'];\r\n");
      out.write("    		var dayOfWeek = days[nowday.getDay()];\r\n");
      out.write("    		//살았다..\r\n");
      out.write("    		//여기는 오늘말고 다른날짜들 버튼 누를시 상단에 값나올곳\r\n");
      out.write("    		\r\n");
      out.write("    		var tag = \"<div><img src='/mini/images/weather/\"+max_min_tempData[date].icon+\".png'/>\";\r\n");
      out.write("    		tag +=\"<span>\"+max_min_tempData[date].max+\"<span>°C</div>\";\r\n");
      out.write("    		tag +=\"<div><div class='nowdata'>강수확률:\"+weather.list[i].pop+\"%</div><div class='nowdata'>습도:\"+weather.list[i].main.humidity+\"%</div>\";\r\n");
      out.write("    		tag +=\"<div>풍속:\"+weather.list[i].wind.speed+\"m/s</div></div>\";\r\n");
      out.write("    		tag +=\"<div><div>날씨</div><div>\"+dayOfWeek+\"요일</div>\";\r\n");
      out.write("    		if(max_min_tempData[date].icon=='09n'){\r\n");
      out.write("    			tag	+=	\"<div>비</div>\";\r\n");
      out.write("    		}else{\r\n");
      out.write("    			tag+=\"<div>\"+weather.list[i].weather[0].description+\"</div>\";\r\n");
      out.write("    		}\r\n");
      out.write("    		\r\n");
      out.write("    		 $(\"#nowweather\").html(tag);\r\n");
      out.write("    		console.log(weather.list[i]);\r\n");
      out.write("    	}else{\r\n");
      out.write("    		//여기는 오늘날짜 현재시간에 가장 가까운 온도나오게 하기.\r\n");
      out.write("    	}\r\n");
      out.write("    	}\r\n");
      out.write("    	\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<!-- HTML 부분 -->\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("    <!-- 오늘의 날씨가 표시될곳 -->\r\n");
      out.write("    <div id=\"nowweather\"> </div>\r\n");
      out.write("    <!-- 차트가 표시될 영역 -->\r\n");
      out.write("    <div style=\"border:1px solid #ccc;\">\r\n");
      out.write("        <canvas id=\"myChart\" style=\"width:100%; height:300px;\"></canvas>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <!-- 날짜 버튼이 표시될 영역 -->\r\n");
      out.write("    <div id=\"buttonContainer\" style=\"margin-top: 20px;\">\r\n");
      out.write("        <!-- 날짜별 버튼이 여기에 추가됨 -->\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<style>\r\n");
      out.write("    #nowweather{\r\n");
      out.write("     display: flex;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("</style>");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/mini/css/footer.css\" type=\"text/css\"/>\r\n");
      out.write("   <footer>\r\n");
      out.write("      <div class=\"inner\">\r\n");
      out.write("        <div class=\"footer-message\">부산의 축제와 맛집을 소개하는 웹사이트</div>\r\n");
      out.write("        <div class=\"footer-contact\">컨택: ict@google.com</div>\r\n");
      out.write("        <div class=\"footer-copyright\">Copyright 2024 All ⓒ rights reserved</div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </footer>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
