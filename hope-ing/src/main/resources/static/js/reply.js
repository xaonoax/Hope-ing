/*
	Hope-ing Reply
	Artist : A-on
 */
 
let replyService = {
	add : (reply_content, callback, error) => {
		$.ajax({
			type : "POST",
			url : "/hope-ing/community/replies/new",
			data : JSON.stringify(reply_content),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			}
		});
	},
	
	getList : (param, callback, error) => {
		let reply_bno = param.reply_bno;
		// var v = a || b : a에 값이 있으면 a, 없으면 b를 v에 할당
		let page = param.page || 1;
		
		$.getJSON("/hope-ing/community/replies/pages/" + reply_bno + "/" + page,
			function(list){
				if(callback){
					callback(list);
				}
			}
		);
	},
	
	remove : (reply_no, callback, error) => {
		$.ajax({
			type : "DELETE",
			url : "/hope-ing/community/replies/" + reply_no,
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			}
		});
	},
	
	update : (reply_content, callback, error) => {
		$.ajax({
			type : "PATCH",
			url : "/hope-ing/community/replies/" + reply_content.reply_no,
			data : JSON.stringify(reply_content),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			}
		});
	},
	
	get : (reply_no, callback, error) => {
		$.get("/hope-ing/community/replies/" + reply_no,
			function(result){
				if(callback){
					callback(result);
				}
			}
		)
	}
};
