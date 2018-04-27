/*
 * application应用组件，负责执行整个应用的高级命令，并且支持命令的撤销和重做。高级命令需要注册才能执行
 * 最后附带了actionFactory，用于简单命令的管理
 */
(function($) {
	$.widget("ccui.application", {
		// 初始化应用
		_init : function() {
			this.commands = [];// 执行过exec的命令，进入commands数组
			this.undoCommands = [];// undo的命令，退出commands，进入undoCommands。redo的命令，退出undoConnamds，进入commands
			this.registedCommands = [];// 注册过的命令，才支持撤销和重做
			this.element.addClass('ccui-application');
			this._defaultHtml()
		},
		_defaultHtml : function() {
		},
		// 执行命令
		executeCommand : function(commandName, commandOptions) {
			// 执行redo或undo命令，之后执行afterCommand命令
			if (commandName == 'redo' || commandName == 'undo') {
				this[commandName]();
				if ($.isFunction(this.options.afterCommand)) {
					this.options.afterCommand.apply(this.element[0])
				}
				return
			}
			// 执行命令，更新commands和undoCommands数组，最后执行afterCommand命令
			if (typeof(commandName) == 'string'
					&& this.registedCommands[commandName]) {

				commandOptions = $.extend({}, commandName.defaults,
						commandOptions);
				commandName = $.extend({
							options : commandOptions
						}, this.registedCommands[commandName]);
				// 删除节点和线时，复制原来节点的属性
//				if ((commandName.defaults)
//						&& (commandName.defaults.text == '删除')) {
//					$(this.commands).each(function(index, element) {
//						if (element.options.id == commandName.options.id) {
//							var offset;
//							if(commandName.options.offset){
//							offset = commandName.options.offset;
//							}
//							$.extend(commandName.options, element.options);
//							if(commandName.options.offset){
//							$.extend(commandName.options, {offset : offset});
//							}
//						}
//					})
//				}
				this.currentCommand = commandName;
				var commandResult = this.exec();
				this.commands.push(commandName);
				if (commandResult !== null && commandResult === false) {
					this.commands.pop()
				} else {
					this.undoCommands = [];
					if ($.isFunction(this.options.afterCommand)) {
						this.options.afterCommand.apply(this.element[0])
					}
				}
			}
		},
		exec : function(a) {
			var b = this.currentCommand, execReturn = b.exec(a);
			$.ccui.log.info('执行动作：' + b.getTitle());
			return execReturn;
		},
		undo : function() {
			if (this.commands.length > 0) {
				var a = this.commands.pop();
				a.undo();
				this.undoCommands.push(a);
				$.ccui.log.info('撤销动作：' + a.getTitle())
			}
		},
		redo : function() {
			if (this.undoCommands.length > 0) {
				var a = this.undoCommands.pop();
				this.currentCommand = a;
				this.commands.push(a);
				this.exec(true);
				$.ccui.log.info('重做动作：' + a.getTitle())
			}
		},
		registerCommand : function(a, b) {
			this.registedCommands[a] = b
		},
		getCommands : function() {
			return [].concat(this.commands)
		},
		getUndoCommands : function() {
			return [].concat(this.undoCommands)
		},
		destroy : function() {
		}
	});
	$.extend($.ccui.application, {
				defaults : {
					menu : '',
					toolbar : '',
					propertyTable : '',
					type : ''
				},
				getter : ['getCommands', 'getUndoCommands']
			})
})(jQuery);
(function(e) {
	this.version = '(beta)(0.0.1)';
	this.actions = [];
	this.register = function(b, c, d) {
		if (e.isFunction(c)) {
			d = c;
			c = {}
		}
		this.actions[b] = function(a) {
			d.apply(this, [a])
		};
		return e
	};
	this.execute = function(a, b) {
		this.actions[a](b)
	};
	this.remove = function(a) {
		return e
	};
	e.actionFactory = this;
	return e
})(jQuery);