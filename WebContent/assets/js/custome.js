$(function() {
	$('#tags').tagsly({
		placeholder : 'タグ!',
		maxItems : 5
	});
});

$('.delete').click(function() {
	if (confirm("本当に削除したい？")) {
		return true;
	} else {
		return false;
	}
});

$('.add-comment')
		.click(
				function(e) {
					var post_id = $('input[name="post_id"]').val();
					var content = $('.comment-content').val();
					num_of_comment = $('.num_of_comment');

					$
							.post({
								url : 'http://localhost:8080/broad//comment/add',
								data : {
									"post_id" : post_id,
									"content" : content
								},
								dataType : 'json',
								success : function(data) {
									$('.comment-content').val("");
									$('.num_of_comment')
											.text(
													parseInt(num_of_comment
															.text(), 10) + 1);
									var new_content = '<div class="row '
											+ data.comment['commentId']
											+ '"><div class="col-sm-1"> <div class="thumbnail"> <img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png"></div></div><div class="col-sm-11"> <div class="panel comment panel-info"> <div class="panel-heading"><strong>'
											+ data.user["email"]
											+ '</strong> <span class="text-muted">コメント <span class="date"> <small>'
											+ data.comment['createAt']
											+ '</small>'
											+ '<a href="#" class="pull-right" onClick="deleteComment('
											+ data.comment['commentId']
											+ ')"><i class="glyphicon glyphicon-remove"></i></a></span></span></div><div class="panel-body">'
											+ data.comment['content']
											+ '</div></div> </div></div>';
									$('.list_comment').after(new_content);
								}
							});
					return false;
				});
function deleteComment(commentId) {

	comment = document.getElementsByClassName(commentId)[0];
	num_of_comment = $('.num_of_comment');
	if (confirm("本当に削除したい？")) {
		$.get({
			url : 'http://localhost:8080/broad//comment/delete?comment_id='
					+ commentId,
			data : {},
			success : function(data) {
				comment.remove();
				$('.num_of_comment').text(
						parseInt(num_of_comment.text(), 10) - 1);
			}
		});
	}
	return false;
}