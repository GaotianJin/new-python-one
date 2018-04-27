/*
 * propertyTable属性表格组件，负责点击中心编辑区域的某个节点后，在屏幕右边区域显示属性数值，并可调用fieldEditor组件，修改节点属性
 */
(function($) {
	$.widget("ccui.propertyTable", {

		options : {},

		_init : function() {
			var self = this;
			this.element.addClass('ccui-propertyTable disabled');
			this._initAction();
			this.element.find('li').each(function() {
				var propertyName = this.firstChild.nodeValue;
				this.firstChild.nodeValue = '';// 清空文本值
				$(this)
						.prepend('<table width="100%" cellspacing="0" cellspadding="0"><tr><td class="name '
								+ this.className
								+ '">'
								+ propertyName
								+ '</td><td class="value">&nbsp;</td></tr></table>');
			});

			$('body', document).click(function() {
						if (self.currentFieldEditor) {
							if (self.currentFieldEditor.fieldEditor) {
								self.currentFieldEditor.fieldEditor('close');
								self.currentFieldEditor = {};
							}
						}
					});
		},

		_initAction : function() {
			var self = this;
			this.element.bind('click', function(event) {
				if ($(this).hasClass('disabled')) {
					return;
				}
				if (self.currentFieldEditor) {
					if (self.currentFieldEditor.fieldEditor) {
						self.currentFieldEditor.fieldEditor('close');
						self.currentFieldEditor = {};
					}
				}
				var clickElement = $(event.srcElement || event.target);
				if (clickElement.is('.propertyGroup')) {
					clickElement.toggleClass('expanded');
					clickElement.parents('li.propertyGroup')
							.toggleClass('expanded');
				} else if (clickElement.is('.value')) {
					var propertyElement = clickElement.parents('li.property'), editor = propertyElement
							.attr('editor'), id = propertyElement.attr('id'), editorName = propertyElement
							.attr('editorName')
							|| id;
					// 如果点击的是属性值元素，新建打开fieldEditor组件实例。
					// editorChange回调函数是workflow组件提供的，可以在修改节点属性后更新中央区域的节点的属性。
					clickElement.fieldEditor({
								editor : editor,
								editorName : editorName,
								beforeClose : self.editorChange
							});
					self.currentFieldEditor = clickElement;
					return false;
				}
			});
		},

		_setProperty : function(property, value) {
			if (value && value != this._getProperty(property)) {
				this.element.find('li#' + property + ' .value:first')
						.text(value).attr('title', value);
			}
		},

		_getProperty : function(property) {
			var value = this.element.find('li#' + property + ' .value:first')
					.text();
			return value;
		},

		setProperty : function(property, value) {
			this._setProperty(property, value);
		},

		// 点击中央编辑区域的节点时，在属性表格显示该节点的属性。
		fillProperties : function(propertyMap, editorChange) {
			this.activeEditor();
			this.reset();
			this.editorChange = editorChange;// 供编辑器回调设置使用
			var value = null;
			for (var property in propertyMap) {
				value = propertyMap[property];
				if (value) {
					this._setProperty(property, value);
				}
				value = null;
			}
		},
		/**
		 * 是表格成为可编辑状态
		 */
		activeEditor : function() {
			this.element.removeClass('disabled');
		},

		reset : function() {
			this.element.find('li').not('.propertyGroup').find('.value')
					.html('&nbsp;');
		}

	});
})(jQuery);
