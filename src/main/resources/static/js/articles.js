// Thymeleaf에서 사용할 JavaScript 코드
function fetchArticles() {
    fetch('/api/articles')
        .then(response => response.json())
        .then(data => {
            const articleList = document.querySelector('tbody');
            articleList.innerHTML = ''; // 기존 목록 초기화

            data.forEach(article => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${article.id}</td>
                    <td><a href="/articles/${article.id}">${article.title}</a></td>
                    <td>${article.content}</td>
                `;
                articleList.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching articles:', error);
        });
}

// 페이지 로드 시 실행되는 함수
window.onload = function() {
    fetchArticles(); // 페이지 로드 시 글 목록을 가져옴

    // 'New Article' 링크 클릭 시 '/api/articles/new'로 리다이렉션
    const newArticleLink = document.querySelector('.btn-primary');
    if (newArticleLink) {
        newArticleLink.addEventListener('click', function(event) {
            event.preventDefault(); // 기본 동작(링크 이동) 방지
            window.location.href = '/api/articles/new'; // '/api/articles/new'로 리다이렉션
        });
    }
};




