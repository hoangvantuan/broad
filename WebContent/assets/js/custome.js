$(function() {
	$('#tags').tagsly({
		placeholder : 'タグ!',
		maxItems : 5
	});
});

$('.add-comment').click(function() {

	var post_id = $('input[name="post_id"]').val();
	var content = $('.comment-content').val();
	$.post({
		url : 'http://localhost:8080/broad//comment/add',
		data : {"post_id" : post_id, "content" : content },
		dataType : 'json',
		success : function(data) {

		}
	})
	return false;
})