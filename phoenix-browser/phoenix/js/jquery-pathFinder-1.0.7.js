/*!
 * jQuery xpathFinder v1.0.7
 * http://www.mashupeye.com/
 *
 * Copyright 2011, MashupEye
 * GPL Version 2 licenses.
 *
 * Date: Thu Sep 3 23:17:53 2011
 */
(function($){
	$.fn.getQuery = function(options){
		o = $.extend({
			type: 'xpath',	//生成类型  'xpath' or 'selector'
			highLight : true, //选择的元素是否高亮显示
			fullPath : false, //是否是全路径
			preferenceAttr: 'id', //属性偏好 'id' or 'class'
			bgColor: 'yellow',	//背景色
			border:'yellow 1px solid',//边框
			expansion: 3,//扩大边框
		}, options||{});
		if(o.highLight){
			this.highLight(o);
		}
		
		var path = getPath(this, '');
		selector = path.replaceAll('/', '>').replaceAll('\\[', ':eq(').replaceAll('\\]', ')').replaceAll('\\:eq\\(\\@', '[').replaceAll('\'\\)', '\']');
		query = '/' + path;
		if(!o.fullPath){
			query = '/' + query;
		}
		if(o.type=='selector'){
			return selector;
		} else {
			return query;
		}
	}

	this.getXpath = function(){
		return query;
	}

	this.getSelector = function(){
		return selector;
	}

	$.fn.highLight = function (options){
		op = $.extend({
			bgColor: 'yellow',	//背景色
			border:'yellow 1px solid',//边框
			expansion: 3,//扩大边框
		}, options||{});
		$('body').append("<div id='abs-box' class='abs'>&nbsp;</div>");
		$('head').append("<style>.abs{position:absolute;zoom:1;pointer-events:none;z-index:-1;}</style>");
		var div = $('#abs-box');
		if(div != this){
			var pos=this.offset(), em = op.expansion;
			div.css({'left':pos.left-em,'top':pos.top-em,'width':this.width()+2*em,'height':this.height()+2*em});
			div.css({'background-color':op.bgColor, 'border':op.border});
		}
	}
		
	function getPath (e, path){
		var tn = e.get(0).tagName;
		if(isNullOrEmpty(e) || isNullOrEmpty(tn)){
			return path;
		}
		var attr = getAttr(e);
		tn = tn.toLowerCase() + attr;
		path = isNullOrEmpty(path) ? tn : tn + "/" + path;
		var parentE = e.parent();
		if(isNullOrEmpty(parentE) || (!o.fullPath && attr.substring(0, 5) == '[@id=')){
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
			if(o.preferenceAttr.toLowerCase() == 'class'){
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
				if(o.type=='xpath'){
					i++;
				}
				return '[' + i + ']';
			} else {
				return '';
			}
		}
	}

	function isNullOrEmpty (o){
		return null==o || 'null'==o || ''==o || undefined==o;
	}

})(jQuery);

String.prototype.replaceAll = function(regx,t){   
	return this.replace(new RegExp(regx,'gm'),t);   
};
