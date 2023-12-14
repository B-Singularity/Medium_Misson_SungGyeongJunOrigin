// articles-new.js 파일

function submitNewArticle() {
    const form = document.querySelector('#newArticleForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // 폼 기본 동작 방지

        const formData = new FormData(form);

        fetch('/articles/create', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                alert('게시글이 성공적으로 작성되었습니다.');
                window.location.href = '/articles'; // 게시글 목록 페이지로 이동
            })
            .catch(error => {
                console.error('There was an error!', error);
                alert('게시글 작성에 실패했습니다.');
            });
    });
}

window.onload = function() {
    submitNewArticle(); // 페이지 로드 시 게시글 작성 이벤트 처리
};
