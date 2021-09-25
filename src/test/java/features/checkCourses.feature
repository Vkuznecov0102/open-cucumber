Feature: Получение курсов валют


  Scenario Outline: Получение курсов
    Given перейти на сайт <url>
    Then вбить в поиске <bankName>
    Then нажать на <button>
    Then проверить что в списке есть <openUrl>
    Then перейти на сайт
    Then получить курсы валют
    And закрыть браузер

    Examples:
      | url                  | bankName   | button  | openUrl
      | "https://google.com" | "Открытие" | "Найти" | "https://www.open.ru"


