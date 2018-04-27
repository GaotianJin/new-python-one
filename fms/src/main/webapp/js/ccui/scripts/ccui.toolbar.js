/*
 * toolbar工具栏组件，负责工具栏的显示、命令等动作
 */
(function($) {
	$.widget("ccui.toolbar", {
		_init : function() {
			this.element.addClass('ccui-toolbar').disableSelection();
			this._initAction()
		},
		_initAction : function() {
			var self = this;
			// 点击某个工具按钮时，触发定义的command或action
			this.element.bind('click', function(event) {
				var clickElement = $(event.srcElement || event.target);
				if (clickElement.is("i[class^='icon']")) {
					clickElement = clickElement.parent();
				}
				if (clickElement.hasClass('disabled')) {
					return
				}
				if (clickElement.hasClass('toolbarItem')) {
					var command = clickElement.attr('command'), action = clickElement
							.attr('action');
					if (command && $.isFunction(self.options.command)) {
						self.options.command.apply(this, [event, {
											command : command
										}])
					}
					if (action && $.actionFactory) {
						$.actionFactory.execute(action)
					}
				}
			})
		},
		//激活按钮
		active : function(actives) {
			if (!actives) {
				return
			}
			for (var i = 0; i < actives.length; i++) {
				this.element.find('button.disabled#' + actives[i])
						.removeClass('disabled')
			}
		},
		//使失效按钮
		disable : function(disables) {
			if (!disables) {
				return
			}
			for (var i = 0; i < disables.length; i++) {
				this.element.find('button.toolbarItem#' + disables[i])
						.addClass('disabled')
			}
		},
		destroy : function() {
		}
	})
})(jQuery);