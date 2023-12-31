# План автоматизации тестирования
#### Валидные данные:
1.	Номер карты: 16 цифр
2.	Фамилия Имя владельца карты: Латинские буквы
3.	Срок действия карты:
- Месяц: 2 цифры в диапазоне от 01 до 12, но не ранее текущего месяца, текущего года
- Год: 2 цифры, не ранее текущего года и не позднее 5 лет, от указанного года
4.	CVC: Любые комбинации из трех цифр

#### Карты предоставленные для тестирования:
- Одобренная карта: 4444 4444 4444 4441; Статус: APPROVED
- Отклоненная карта: 4444 4444 4444 4442; Статус: DECLINED

# Автоматические сценарии:
#### Положительные сценарии:
**1 Сценарий**. Оплата путем нажатия кнопки «Купить» с помощью одобренной карты. Поля заполнены Валидными данными. 
Шаги воспроизведения:

1. В браузере перейти по адресу localhost:8080 
2. На главной странице сервиса нажать кнопку "Купить"
3. В поле "Номер карты" ввести: 4444 4444 4444 4441 
4. В поле "Месяц" ввести: 12
5. В поле "Год" ввести: 23 
6. В поле "Владелец" ввести фамилию и имя на латинском языке 
7. В поле "CVC/CVV" ввести: 123 
8. Нажать кнопку "Продолжить"

Ожидаемый результат: Оплата прошла, отобразилось уведомление «Успешно. Операция одобрена Банком». В Базе Данных появилась запись со статусом APPROVED

**2 Сценарий**. Оплата путем нажатия кнопки «Купит в кредит» с помощью одобренной карты. Поля заполнены Валидными данными.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести: 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Оплата прошла, отобразилось уведомление «Успешно. Операция одобрена Банком». В Базе Данных появилась запись со статусом APPROVED

#### Отрицательные сценарии:
**1 Сценарий**. Оплата путем нажатия кнопки «Купить» с помощью отклоненной карты, поля заполнены валидными значениями.  
Шаги воспроизведения:

1.  В браузере перейти по адресу localhost:8080
2.  На главной странице сервиса нажать на кнопку "Купить"
3.  В поле "Номер карты" ввести: 4444 4444 4444 4442
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Оплата не прошла, отобразилось уведомление «Ошибка. Банк отказал в проведении операции». В Базе Данных появилась запись со статусом DECLINED

**2 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с помощью отклоненной карты, поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	В браузере перейти по адресу localhost:8080
2.	На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести: 4444 4444 4444 4442
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Оплата не прошла, отобразилось уведомление «Ошибка. Банк отказал в проведении операции». В Базе Данных появилась запись со статусом DECLINED

**3 Сценарий**. Оплата путем нажатия кнопки «Купить» с помощью карты отсутствующей в Базе Данных, поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	В браузере перейти по адресу localhost:8080
2.	На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести: 4444 4444 4444 2222
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Оплата не прошла, отобразилось уведомление «Ошибка. Банк отказал в проведении операции». В Базе Данных появилась запись со статусом DECLINED

**4 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с помощью карты отсутствующей в Базе Данных, поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	В браузере перейти по адресу localhost:8080
2.	На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести: 4444 4444 4444 2222
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Оплата не прошла, отобразилось уведомление «Ошибка. Банк отказал в проведении операции». В Базе Данных появилась запись со статусом DECLINED

**5 Сценарий**. Оплата путем нажатия кнопки «Купить» с пустым полем «Номер карты», остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	Поле "Номер карты" оставить пустым.
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Поле «Номер карты» подсвечено красным цветом, появляются сообщения об ошибке: "Неверный формат" под полем "Номер карты". Запись в БД информации о совершенных операциях не происходит.

**6 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с пустым полем «Номер карты», остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	Поле "Номер карты" оставить пустым.
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Поле «Номер карты» подсвечено красным цветом, появляются сообщения об ошибке: "Неверный формат" под полем "Номер карты". Запись в БД информации о совершенных операциях не происходит.

**7 Сценарий**. Оплата путем нажатия кнопки «Купить» в поле «Номер карты» ввести недостаточное количество цифр, остальные поля заполнены валидными значениями. 
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 1234
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Поле «Номер карты» подсвечено красным цветом, появляются сообщения об ошибке: "Неверный формат" под полем "Номер карты". Запись в БД информации о совершенных операциях не происходит.

**8 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» в поле «Номер карты» ввести недостаточное количество цифр, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 1234
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Поле «Номер карты» подсвечено красным цветом, появляются сообщения об ошибке: "Неверный формат" под полем "Номер карты". Запись в БД информации о совершенных операциях не происходит.

**9 Сценарий**. Оплата путем нажатия кнопки «Купить» с истекшим сроком действия карты, остальные поля заполнены валидными значениями. 
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 01
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить» 

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Месяц». Запись в БД информации о совершенных операциях не происходит.

**10 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с истекшим сроком действия карты, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:
   
 1.	 В браузере перейти по адресу localhost:8080
 2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
 3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
 4.	В поле "Месяц" ввести: 01
 5.	В поле "Год" ввести: 23
 6.	В поле "Владелец" ввести фамилию и имя на латинском языке
 7.	В поле "CVC/CVV" ввести: 123
 8.	Нажать кнопку "Продолжить» 

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Месяц». Запись в БД информации о совершенных операциях не происходит.

**11 Сценарий**. Оплата путем нажатия кнопки «Купить» с недействительным указанием месяца, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 00
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Месяц». Запись в БД информации о совершенных операциях не происходит.

**12 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с недействительным указанием месяца, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 00
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить» 

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Месяц». Запись в БД информации о совершенных операциях не происходит.

**13 Сценарий**. Оплата путем нажатия кнопки «Купить» с недействительным указанием месяца, остальные поля заполнены валидными значениями. 
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 13
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется.Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Месяц». Запись в БД информации о совершенных операциях не происходит.

**14 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с недействительным указанием месяца, остальные поля заполнены валидными значениями. 
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 13
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Месяц». Запись в БД информации о совершенных операциях не происходит.

**15 Сценарий**. Оплата путем нажатия кнопки «Купить», поле месяца оставить пустым, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	Поле "Месяц" оставить пустым.
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Поле «Месяц» подсвечено красным цветом, появляются сообщения об ошибке: "Поле обязательно для заполнения" под полем «Месяц». Запись в БД информации о совершенных операциях не происходит.

**16 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с недействительным указанием месяца, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	Поле "Месяц" оставить пустым
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Поле «Месяц» подсвечено красным цветом, появляются сообщения об ошибке: "Поле обязательно для заполнения" под полем «Месяц». Запись в БД информации о совершенных операциях не происходит.

**17 Сценарий**. Оплата путем нажатия кнопки «Купить» с истекшим сроком действия карты, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1. В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 22
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется.Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Год». Запись в БД информации о совершенных операциях не происходит.

**18 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с истекшим сроком действия карты, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:
    
1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 22
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Год». Запись в БД информации о совершенных операциях не происходит.

**19 Сценарий**. Оплата путем нажатия кнопки «Купить» с указанием срока действия карты свыше допустимого, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 29
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется.Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Год». Запись в БД информации о совершенных операциях не происходит.

**20 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с указанием срока действия карты свыше допустимого, остальные поля заполнены валидными значениями. 
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 29
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123 
8. Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Год». Запись в БД информации о совершенных операциях не происходит.

**21 Сценарий**. Оплата путем нажатия кнопки «Купить» поле «год» останется пустым, остальные поля заполнены валидными значениями. 
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	Поле "Год" оставить пустым
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Месяц». Запись в БД информации о совершенных операциях не происходит.

**22 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» поле «год» останется пустым, остальные поля заполнены валидными значениями. 
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	Поле "Год" оставить пустым
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверно указан срок действия карты" под полем «Месяц». Запись в БД информации о совершенных операциях не происходит.

**23 Сценарий**. Оплата путем нажатия кнопки «Купить» с заполненным полем «Владелец» на кириллице, остальные поля заполнены валидными значениями.
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на кириллице
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверный формат" под полем «Владелец». Запись в БД информации о совершенных операциях не происходит.

**24 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с заполненным полем «Владелец» на кириллице, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на кириллице
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверный формат" под полем «Владелец». Запись в БД информации о совершенных операциях не происходит.

**25 Сценарий**. Оплата путем нажатия кнопки «Купить» с указанием только имени в поле «Владелец», остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести только имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверный формат" под полем «Владелец». Запись в БД информации о совершенных операциях не происходит.

**26 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит» с указанием только имени в поле «Владелец», остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести только имя на латинском языке
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверный формат" под полем «Владелец». Запись в БД информации о совершенных операциях не происходит.

**27 Сценарий**. Оплата путем нажатия кнопки «Купить» поле «Владелец» останется пустым, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	Поле "Владелец" оставить пустым
7.	В поле "CVC/CVV" ввести: 123 
8. Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Поле обязательно для заполнения" под полем «Владелец». Запись в БД информации о совершенных операциях не происходит.

**28 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит», поле «Владелец» останется пустым, остальные поля заполнены валидными значениями. 
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	Поле "Владелец" оставить пустым
7.	В поле "CVC/CVV" ввести: 123
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке:"Поле обязательно для заполнения" под полем «Владелец». Запись в БД информации о совершенных операциях не происходит.

**29 Сценарий**. Оплата путем нажатия кнопки «Купить» в поле «CVC/CVV» указаны невалидные данные, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке 
7. В поле "CVC/CVV" ввести: 121
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверный формат" под полем «CVC/CVV». Запись в БД информации о совершенных операциях не происходит.

**30 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит», в поле «CVC/CVV» указаны невалидные данные, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	В поле "CVC/CVV" ввести: 121
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Неверный формат" под полем «CVC/CVV». Запись в БД информации о совершенных операциях не происходит.

**31 Сценарий**. Оплата путем нажатия кнопки «Купить» поле «CVC/CVV» останется пустым, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	Поле "CVC/CVV" оставить пустым
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке: "Поле обязательно для заполнения" под полем «CVC/CVV». Запись в БД информации о совершенных операциях не происходит.

**32 Сценарий**. Оплата путем нажатия кнопки «Купить в кредит», поле «CVC/CVV» останется пустым, остальные поля заполнены валидными значениями.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	В поле "Номер карты" ввести (например): 4444 4444 4444 4441
4.	В поле "Месяц" ввести: 12
5.	В поле "Год" ввести: 23
6.	В поле "Владелец" ввести фамилию и имя на латинском языке
7.	Поле "CVC/CVV" оставить пустым
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Появляются сообщения об ошибке:"Поле обязательно для заполнения" под полем «CVC/CVV». Запись в БД информации о совершенных операциях не происходит.

**33 Сценарий**. Оплата путем нажатия кнопки «Купить» все поля остаются пустыми.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить"
3.	Поле "Номер карты" оставить пустым
4.	Поле "Месяц"оставить пустым
5.	Поле "Год" оставить пустым
6.	Поле "Владелец" оставить пустым
7.	Поле "CVC/CVV" оставить пустым
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Все поля подсвечиваются красным. Появляются сообщения об ошибке:"Поле обязательно для заполнения" под формами заполнения. Запись в БД информации о совершенных операциях не происходит.

34 Сценарий. Оплата путем нажатия кнопки «Купить в кредит», все поля остаются пустыми.  
Шаги воспроизведения:

1.	 В браузере перейти по адресу localhost:8080
2.	 На главной странице сервиса нажать на кнопку "Купить в кредит"
3.	Поле "Номер карты" оставить пустым
4.	Поле "Месяц"оставить пустым
5.	Поле "Год" оставить пустым
6.	Поле "Владелец" оставить пустым
7.	Поле "CVC/CVV" оставить пустым
8.	Нажать кнопку "Продолжить»

Ожидаемый результат: Форма не отправляется. Все поля подсвечиваются красным. Появляются сообщения об ошибке:"Поле обязательно для заполнения" под формами заполнения. Запись в БД информации о совершенных операциях не происходит.

# Перечень используемых инструментов:

1.	Intellij IDEA
Редактор кода для подготовки авто-тестов 
2. JDK 11
Программный пакет, который необходим для создания Java-приложений, на котором будет написан дипломный проект
3.	Gradle
Система управления проектом. Большое количество библиотек
4.	JUnit 5
Платформа для написания и запуска авто-тестов
5.	Selenide
Фреймворк для автоматизированного тестирования ПО и веб-приложений
6.	Lombok
Библиотека, помогающая сократить время написания кода. Автоматически генерирует конструкторы (Пример: Getter/Setter)
7.	Faker
Библиотека для генерации тестовых данных
8.	Docker
Через Докер используются приложения, использование которых может вызывает некоторые риски при тестировании.
9.	MySQL
Система управления БД
10.	PostgreSQL
Система управления БД с открытым исходным кодом
11.	Git/GitHub
Система контроля версий. Дает возможность публикации проекта на удаленный репозиторий. Позволяет возвращаться на прошлые этапы работы над проектом.
12.	Allure
Детальный просмотр отчета о прохождении авто-тестов

# Перечень и описание возможных рисков при автоматизации:
- Отсутствие документации на тестируемое приложение 
- Зависимость автоматических тестов от текущей версии приложения
- Сложность настройки инструментов для тестирования, поднятие БД

# Интервальная оценка с учетом рисков:
1.	Планирование тестирования - 3 часа
2.	Подготовка к тестированию - 9 часов
3.	Написание кода - 50 часов
4.	Составление баг-репортов - 6 часов
5.	Подготовка отчетов - 12 часов

# План сдачи работ:
- Готовность авто-тестов после согласования плана тестирования: - 62 часа, ориентировочная дата: 06.11.23 
- Составление отчетов по итогам тестирования - 12 часов, 07.11.23 
- Составление отчетов по итогам автоматизации - 6 часов 08.11.23
