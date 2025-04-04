document.addEventListener('DOMContentLoaded', function() {
    const vacationForm = document.getElementById('vacationForm');
    const resultContainer = document.getElementById('resultContainer');
    const vacationPayElement = document.getElementById('vacationPay');

    // Устанавливаем текущую дату в качестве значения по умолчанию
    const today = new Date().toISOString().split('T')[0];
    document.getElementById('startDate').value = today;
    document.getElementById('endDate').value = today;

    vacationForm.addEventListener('submit', function(e) {
        e.preventDefault(); // Важно: предотвращаем отправку формы

        const startDate = document.getElementById('startDate').value;
        const endDate = document.getElementById('endDate').value;
        const avgAnnualSalary = document.getElementById('avgAnnualSalary').value;

        // Показываем индикатор загрузки
        const submitButton = vacationForm.querySelector('button[type="submit"]');
        submitButton.disabled = true;
        submitButton.textContent = 'Рассчитываем...';

        // Форматируем даты в формат dd.MM.yyyy
        const formatDate = (dateString) => {
            const [year, month, day] = dateString.split('-');
            return `${day}.${month}.${year}`;
        };

        // Отправляем запрос на сервер
        fetch(`/calculate?startDate=${formatDate(startDate)}&endDate=${formatDate(endDate)}&avgAnnualSalary=${avgAnnualSalary}`)
            .then(response => {
                if (response.ok) {
                        return response.json().then(data => {
                            // Отображаем результат, если получили JSON
                            vacationPayElement.textContent = data.amount.toFixed(2);
                            resultContainer.style.display = 'block';
                        });
                } else {
                    const contentType = response.headers.get('content-type');
                    if (contentType && contentType.includes('text/html')) {
                        // Если получили HTML
                        return response.text().then(html => {
                            // Заменяем всю страницу полученным HTML
                            document.open();
                            document.write(html);
                            document.close();
                        });
                    } else {
                        alert('Непредвиденная ошибка сервера');
                    }
                }
            })
            .finally(() => {
                // Восстанавливаем кнопку в любом случае
                submitButton.disabled = false;
                submitButton.textContent = 'Рассчитать';
            });
    });
});