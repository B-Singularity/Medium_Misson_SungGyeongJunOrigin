// 게시글 작성 이벤트를 처리하는 함수
function submitNewArticle() {
    const form = document.querySelector('#newArticleForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // 폼 기본 동작 방지

        const formData = new FormData(form);

        // 폼 데이터를 JSON 형태로 변환하여 서버로 전송
        const articleData = {
            title: formData.get('title'),
            content: formData.get('content')
        };

        fetch('/api/articles', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // JSON 형태로 전송함을 명시
            },
            body: JSON.stringify(articleData) // JSON 형태로 변환한 데이터를 전송
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // JSON 형태로 응답 받음
            })
            .then(data => {
                alert('게시글이 성공적으로 작성되었습니다.');
                window.location.href = '/api/articles'; // 수정된 엔드포인트로 이동
            })
            .catch(error => {
                console.error('There was an error!', error);
                alert('게시글 작성에 실패했습니다.');
            });
    });
}


// 페이지 로드 시 게시글 작성 이벤트 처리
window.onload = function() {
    submitNewArticle();
};

