/*
 * fieldEditor属性编辑器组件，负责修改属性
 */
(function($) {
	$.widget("ccui.fieldEditor", {

		options : {
			editor : '',
			editorName : '',
			beforeClose : '',
			fieldType : '',
			selectOptionsMap : [],// 存储select的初始化条件
			dialogOptionsMap : []
			// 存储dialog的初始化条件
		},

		// 根据不同的类型初始化编辑器
		_create : function() {
			var fieldOptions = {
				property : this.options.editor + '-' + this.options.editorName,
				width : 95
			};
			// alert(editorName);
			switch (this.options.editor) {
				case 'input' :
					this._setOptions({
								property : this.options.editorName,
								fieldOptions : fieldOptions,
								fieldType : 'fieldText'
							});
					break;
				case 'select' :
					var selectOptions = $
							.extend(
									{},
									fieldOptions,
									this.options.selectOptionsMap[this.options.editorName]);
					if (!selectOptions) {
						$.ccui.log.info('缺少必要的fieldSelect的初始化条件');
						// throw new Error('');
					}
					this._setOptions({
								property : this.options.editorName,
								fieldOptions : fieldOptions,
								fieldType : 'fieldSelect'
							});
					break;
				case 'dialog' :
					var dialogOptions = $
							.extend(
									{
										autoOpen : false,
										modal : true,
										overlay : {
											opacity : 0.2,
											background : "#A8A8A8"
										},
										buttons : {
											'确定' : function() {
												this.fieldDiv.close();
											},
											'关闭' : function() {
												$(this).dialog('close');
											}
										},
										setValue : function(value) {
											// 设置值
										},
										getValue : function() {
											return '';
										}
									},
									this.options.dialogOptionsMap[this.options.editorName]);
					this._setOptions({
								property : this.options.editorName,
								fieldOptions : fieldOptions,
								fieldType : 'fieldSelect'
							});
					break;
				default :
					;
			}
			var cName = 'editor-' + this.options.fieldType;
			this.fieldDiv = $('<div class="' + cName + '"/>').bind('click',
					function() {
						return false;
					});
			$('body', document).append(this.fieldDiv);
			this.fieldDiv[this.options.fieldType]($.extend({
						property : cName
					}, this.options.fieldOptions));
		},

		// 打开编辑器，编辑区新建fieldDiv对象，原对象为bindElement
		_init : function() {
			var pos = {
				position : 'absolute',
				top : this.element.offset().top - (($.browser.msie) ? 2 : 0),
				left : this.element.offset().left + (($.browser.msie) ? 20 : 0),
				width : this.element.width(),
				height : this.element.height()
			};
			// if(this.fieldType=='fieldSelect')pos.width-=18;
			this.fieldDiv.css(pos).show().fieldValue(this.element.text());
			this.fieldDiv.find('input').focus();
			this.fieldDiv.find('input').css('line-height', '19px');
			this.fieldDiv.css('z-index', '10');
		},

		// 关闭编辑器，修改原点击对象bindElement的值，关闭fieldDiv对象
		close : function() {
			if (this.fieldDiv) {
				var value = this.fieldDiv.fieldValue();
				if(this.options.property == 'text' && (value == '')){
					if(this.element.is('.point')){
						//value = this.element.parent().attr('id');
					}else{
						value = this.element.attr('id');
					}
				}
				this.options.beforeClose
						&& this.options.beforeClose(this.options.property,
								value);
				this.element.html(value).attr('title', this.element.text());
				this.fieldDiv.hide().fieldReset();
			}
		},
		
		_destroy : function() {
			this.close();
			this.fieldDiv.remove();
		},

		// 注册下拉编辑器
		registSelect : function(selectOptions) {
			this.options.selectOptionsMap[this.options.editorName] = selectOptions;
		},

		// 注册弹出框
		registDialog : function(dialogOptions) {
			this.options.dialogOptionsMap[this.options.editorName] = dialogOptions;
		}

	});
})(jQuery);
