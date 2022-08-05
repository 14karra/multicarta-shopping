INSERT INTO public.item(id, name, description, amount, quantity, insertion_date)
VALUES (1, 'Телевизор', 'Отличный телевизор. Очень дешево!', 49999.99, 200,
        CAST(CURRENT_TIMESTAMP AS TIMESTAMP WITH TIME ZONE)),
       (2, 'Смартфон', 'Отличный смартфон. Очень дешево!', 64300, 750,
        CAST(CURRENT_TIMESTAMP AS TIMESTAMP WITH TIME ZONE)),
       (3, 'Соковыжималка', 'Отличная Соковыжималка. Очень дешево!', 3499.99, 52,
        CAST(CURRENT_TIMESTAMP AS TIMESTAMP WITH TIME ZONE)),
       (4, 'Наушники', 'Отличные Наушники. Очень дешево!', 1200, 15,
        CAST(CURRENT_TIMESTAMP AS TIMESTAMP WITH TIME ZONE)),
       (5, 'Клавиатура', 'Отличная Клавиатура. Очень дешево!', 799.99, 14,
        CAST(CURRENT_TIMESTAMP AS TIMESTAMP WITH TIME ZONE));