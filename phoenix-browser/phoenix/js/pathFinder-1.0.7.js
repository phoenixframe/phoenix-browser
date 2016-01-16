
var selector='', xpath='', html='', attributes='', tagName = '';
var tagName2 = '';
/*getQuery*/
var type = 'xpath';
var highLight = true;
var fullPath = false;
var preferenceAttr = 'id';

/*highLight*/
var bgColor = 'yellow';
var border = 'yellow 1px solid';
var expansion = 3;
var result;
function mouseClickSelect(){
	$(document).one('click', function(e){
		e=e||window.event;
		var target=e.target||e.srcElement;
		tagName=getTagName(target);
	    xpath = getQuery($(target));
	
		selector = getSelector();
		attributes = getAttrs(target);
		html = getHtml(target);
		result = "{\"tagName\": \""+tagName+"\",\"xpath\": \""+xpath+"\",\"selector\": \""+selector+"\",\"attributes\": {"+attributes+"},\"innerHTML\": \""+html+"\"}";
		return false;
	});

	return result;
}

/**
 * 得到xpath
 * @returns {String}
 */
function getXpath(){
	return xpath;
}

/**
 * 得到CSS选择器
 * @returns {String}
 */
function getSelector(){
	return selector;
}

/**
 * 得到选择元素的属性
 * @param e
 * @returns {String}
 */
function getAttrs(e){
	var as = "\"";
	if(!isNullOrEmpty(e)){
		var a = e.attributes;
		for(var i=0, len = a.length; i<len; i++){
			if(!isNullOrEmpty(a[i]) && (a[i].specified || a[i].name == 'value') ){
				as += ",\"" +  a[i].name.trim() + "\":\"" + a[i].value.trim() + "\"";
			}
		}
	}
	attributes = as.substring(2, as.length);
	return attributes;
}

/**
 * 得到选择元素的tag标签名
 * @param e
 * @returns
 */
function getTagName(e){
	return tagName = e.tagName.toLowerCase();
}

/**
 * 得到选择元素的html代码
 * @param e
 * @returns {String}
 */
function getHtml(e){
	var tn = getTagName(e), val = $(e).html();
	var attr = attributes;
	html = '<' + tn + ' ' + attr.substring(1, attr.length).replaceAll(',\'', ' ').replaceAll('\':', '=').replaceAll('"', '\\"');
	html += isNullOrEmpty(val) ?  '/>' : '>' + val + '</' + tn + '>';
	return html;
}

/**
 * 隐藏页面高亮选择层
 */
function hideHighLight() {
	$('#abs-box').css({'left':0,'top':0,'width':0,'height':0,'background-color':'white'});
	$(document).click(function(e){hideSelector();}); // 使超链有效
}

function getQuery(e){
	if(highLight){
		showHighLight(e);
	}
	var path = getPath(e, '');
	selector = path.replaceAll('/', '>').replaceAll('\\[', ':eq(').replaceAll('\\]', ')').replaceAll('\\:eq\\(\\@', '[').replaceAll('\'\\)', '\']');
	xpath = '/' + path;
	if(!fullPath){
		xpath = '/' + xpath;
	}
	if(type=='selector'){
		return selector;
	} else {
		return xpath;
	}
}

/**
 * 显示页面高亮选择层
 * @param e
 */
function showHighLight(e){
	$('body').append("<div id='abs-box' class='abs'>&nbsp;</div>");
	$('head').append("<style>.abs{position:absolute;zoom:1;pointer-events:none;z-index:-1;}</style>");
	var div = $('#abs-box');
	if(div != e){
		var pos=e.offset(), em = expansion;
		div.css({'left':pos.left-em,'top':pos.top-em,'width':e.width()+2*em,'height':e.height()+2*em});
		div.css({'background-color':bgColor, 'border':border});
	}
}
		
function getPath (e, path){
	var tn = e.get(0).tagName;
	if(isNullOrEmpty(e) || isNullOrEmpty(tn)){
		return path;
	}
	var attr = getAttr(e);
	tn = tn.toLowerCase() + attr;
	path = isNullOrEmpty(path) ? tn : tn + '/' + path;
	var parentE = e.parent();
	if(isNullOrEmpty(parentE) || (!fullPath && attr.substring(0, 5) == '[@id=')){
		return path;
	}
	return getPath(parentE, path);
}

function getAttr (e){
	var tn = e.get(0).tagName;
	var id = e.attr('id'), clazz = e.attr('class');
	var hasId = !isNullOrEmpty(id), hasClazz = !isNullOrEmpty(clazz);
	id = "[@id='" + id + "']";
	clazz = "[@class='" + clazz + "']";
	if(hasId && hasClazz){
		if(preferenceAttr.toLowerCase() == 'class'){
			return clazz;
		} else {
			return id;
		}
	} else if(hasId && !hasClazz) {
		return id;
	} else if(!hasId && hasClazz) {
		return clazz;
	} else {
		if(e.siblings(tn).size() > 0) {
			var i = e.prevAll(tn).size();
			if(type=='xpath'){
				i++;
			}
			return '[' + i + ']';
		} else {
			return '';
		}
	}
}

function emptyClick() {
	$(document).ready(function(){
		$('head').click(function(){
			alert('aaaa');
		});
	});
}

/**
 * 判断参数不是是null/undefined/空
 * @param o
 * @returns {Boolean}
 */
function isNullOrEmpty (o){
	return null==o || 'null'==o || ''==o || undefined==o;
}

/**
 * 给String加一个replaceAll方法
 * @param regx 要替换的字符串
 * @param t 要替换成的字符串
 */
String.prototype.replaceAll = function(regx,t){   
	return this.replace(new RegExp(regx,'gm'),t);   
};

/**
 * 给String加一个trim方法
 */
String.prototype.trim = function() {
  var str = this,
  whitespace = ' \n\r\t\f\x0b\xa0\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u200b\u2028\u2029\u3000';
  for (var i = 0,len = str.length; i < len; i++) {
    if (whitespace.indexOf(str.charAt(i)) === -1) {
      str = str.substring(i);
      break;
    }
  }
  for (i = str.length - 1; i >= 0; i--) {
    if (whitespace.indexOf(str.charAt(i)) === -1) {
      str = str.substring(0, i + 1);
      break;
    }
  }
  return whitespace.indexOf(str.charAt(0)) === -1 ? str : '';
}
