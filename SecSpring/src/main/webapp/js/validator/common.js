(function () {

    var app = {

        initialize: function () {
            this.setUpListeners();
        },

        setUpListeners: function () {
            $('form').on('submit', app.submitForm);
            $('form').on('keydown', '.has-error', app.removeError);
        },

        submitForm: function (e) {
            e.preventDefault();

            var form = $(this),
                submitBtn = form.find('button[type="submit"]', 'input[type="submit"]');

            // если валидация не проходит - то дальше не идём
            if (app.validateForm(form) === false)    return false;

            this.submit();

        },

        validateForm: function (form) {

            var inputs = form.find('input'),
                valid = true;

            inputs.tooltip('destroy');

            $.each(inputs, function (index, val) {
                var disabled = $(val).attr('disabled');
                if (disabled != 'disabled' && $(val).parents('.form-group.novalid').length==0) {
                    var input = $(val),
                        val = input.val(),
                        formGrout = input.parents('.form-group'),
                        label = formGrout.find('label').text().toLowerCase(),
                        textError = 'Введите ' + label;

                    if (val.length === 0) {
                        formGrout.addClass('has-error').removeClass('has-success');
                        input.tooltip({
                            trigger: 'manual',
                            placement: 'right',
                            title: textError
                        }).tooltip('show');
                        valid = false;
                    } else {
                        formGrout.removeClass('has-error').addClass('has-success');
                        input.tooltip('hide');
                    }
                }
            });
            var valT = inputs.prevObject.context;
            for(var i = 0; i<valT.length;i++){
                var val = valT[i];
               if(val.localName=='input') {
                   var disabled = $(val).attr('disabled');
                   if (disabled != 'disabled' && $(val).parents('.form-group.novalid').length==0) {
                       var input = $(val),
                           val = input.val(),
                           formGrout = input.parents('.form-group'),
                           label = formGrout.find('label').text().toLowerCase(),
                           textError = 'Введите ' + label;

                       if (val.length === 0) {
                           formGrout.addClass('has-error').removeClass('has-success');
                           input.tooltip({
                               trigger: 'manual',
                               placement: 'right',
                               title: textError
                           });
                       input.tooltip('show');
                           valid = false;
                       } else {
                           formGrout.removeClass('has-error').addClass('has-success');
                           input.tooltip('hide');
                       }
                   }
               }
            }
            inputs = form.find('select');
            $.each(inputs, function (index, val) {
                var disabled = $(val).attr('disabled');
                if (disabled != 'disabled') {
                    var input = $(val),
                        val = input.val(),
                        formGrout = input.parents('.form-group'),
                        label = formGrout.find('label').text().toLowerCase(),
                        textError = 'Введите ' + label;

                    if (val.length === 0) {
                        formGrout.addClass('has-error').removeClass('has-success');
                        input.tooltip({
                            trigger: 'manual',
                            placement: 'right',
                            title: textError
                        }).tooltip('show');
                        valid = false;
                    } else {
                        formGrout.removeClass('has-error').addClass('has-success');
                        input.tooltip('hide');
                    }
                }
            });
            var valT = inputs.prevObject.context;
            for(var i = 0; i<valT.length;i++){
                var val = valT[i];
                if(val.localName=='select') {
                    var disabled = $(val).attr('disabled');
                    if (disabled != 'disabled') {
                        var input = $(val),
                            val = input.val(),
                            formGrout = input.parents('.form-group'),
                            label = formGrout.find('label').text().toLowerCase(),
                            textError = 'Введите ' + label;

                        if (val.length === 0) {
                            formGrout.addClass('has-error').removeClass('has-success');
                            input.tooltip({
                                trigger: 'manual',
                                placement: 'right',
                                title: textError
                            });
                            input.tooltip('show');
                            valid = false;
                        } else {
                            formGrout.removeClass('has-error').addClass('has-success');
                            input.tooltip('hide');
                        }
                    }
                }
            }
            inputs = form.find('textarea');
            $.each(inputs, function (index, val) {
                var disabled = $(val).attr('disabled');
                if (disabled != 'disabled') {
                    var input = $(val),
                        val = input.val(),
                        formGrout = input.parents('.form-group'),
                        label = formGrout.find('label').text().toLowerCase(),
                        textError = 'Введите ' + label;

                    if (val.length === 0) {
                        formGrout.addClass('has-error').removeClass('has-success');
                        input.tooltip({
                            trigger: 'manual',
                            placement: 'right',
                            title: textError
                        }).tooltip('show');
                        valid = false;
                    } else {
                        formGrout.removeClass('has-error').addClass('has-success');
                        input.tooltip('hide');
                    }
                }
            });
            var valT = inputs.prevObject.context;
            for(var i = 0; i<valT.length;i++){
                var val = valT[i];
                if(val.localName=='textarea') {
                    var disabled = $(val).attr('disabled');
                    if (disabled != 'disabled') {
                        var input = $(val),
                            val = input.val(),
                            formGrout = input.parents('.form-group'),
                            label = formGrout.find('label').text().toLowerCase(),
                            textError = 'Введите ' + label;

                        if (val.length === 0) {
                            formGrout.addClass('has-error').removeClass('has-success');
                            input.tooltip({
                                trigger: 'manual',
                                placement: 'right',
                                title: textError
                            });
                            input.tooltip('show');
                            valid = false;
                        } else {
                            formGrout.removeClass('has-error').addClass('has-success');
                            input.tooltip('hide');
                        }
                    }
                }
            }
            return valid;

        },

        removeError: function () {
            $(this).removeClass('has-error').find('input').tooltip('destroy');
        }

    };

    app.initialize();

}());