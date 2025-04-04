document.addEventListener('DOMContentLoaded', function() {
    const vacationForm = document.getElementById('vacationForm');
    const resultContainer = document.getElementById('resultContainer');
    const vacationPayElement = document.getElementById('vacationPay');

    // Устанавливаем текущую дату в качестве значения по умолчанию
    const today = new Date().toISOString().split('T')[0];
    document.getElementById('startDate').value = today;
    document.getElementById('endDate').value = today;

    vacationForm.addEventListener('submit', async function(e) {
        e.preventDefault();

        const startDate = document.getElementById('startDate').value;
        const endDate = document.getElementById('endDate').value;
        const avgSalary = document.getElementById('avgSalary').value;

        try {
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
            const response = await fetch(`/calculate?startDate=${formatDate(startDate)}&endDate=${formatDate(endDate)}&avgSalary=${avgSalary}`);

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();

            // Отображаем результат
            vacationPayElement.textContent = data.amount.toFixed(2);
            resultContainer.style.display = 'block';

        } catch (error) {
            console.error('Ошибка при расчете отпускных:', error);
            alert('Произошла ошибка при расчете отпускных. Пожалуйста, проверьте введенные данные и попробуйте снова.');
        } finally {
            // Восстанавливаем кнопку
            const submitButton = vacationForm.querySelector('button[type="submit"]');
            submitButton.disabled = false;
            submitButton.textContent = 'Рассчитать';
        }
    });
});