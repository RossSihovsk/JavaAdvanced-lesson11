$('button.buy-magazine').click(function() {
    var magazineID = jQuery(this).attr('magazineID');

    console.log("вот то")
    $.post('subscribe', {'magazineID': magazineID},
        function(data) {
            if (data == 'Success') {

                $('#buyMagazineModal').hide();
                $('.modal-backdrop').remove();
                alert('Журнал успішно додано в корзину!');
            }
            else {
                console.log("Не то")
            }
        });
});
