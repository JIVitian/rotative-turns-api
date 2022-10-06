insert into WORKDAY_TYPE
select *
from (select 1, 'normal'
      union
      select 2, 'extra hours'
      union
      select 3, 'vacations'
      union
      select 4, 'free day')
x where not exists(select * from WORKDAY_TYPE);