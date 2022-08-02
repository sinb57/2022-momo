import { useTheme } from '@emotion/react';

import LeftArrow from 'components/svg/LeftArrow';
import RightArrow from 'components/svg/RightArrow';
import useCalendar from 'hooks/useCalendar';

import * as S from './index.styled';

const days = ['일', '월', '화', '수', '목', '금', '토'];

const dayClassGenerator = (day: string | number) => {
  switch (day) {
    case '토':
    case 6:
      return 'sat';
    case '일':
    case 0:
      return 'sun';
    default:
      return '';
  }
};

interface CalendarProps {
  year: number;
  month: number;
  goToPrevMonth: () => void;
  goToNextMonth: () => void;
  today: Date;
  size: 'medium' | 'large';
}

function Calendar({
  year,
  month,
  goToPrevMonth,
  goToNextMonth,
  today,
  size,
}: CalendarProps) {
  const { dates, prevDates, nextDates } = useCalendar(year, month);
  const theme = useTheme();

  const isToday = (date: number) =>
    today.getFullYear() === year &&
    today.getMonth() === month - 1 &&
    today.getDate() === date;

  return (
    <S.Container size={size}>
      <S.Navigator size={size}>
        <S.LeftArrow onClick={goToPrevMonth}>
          <LeftArrow width={30} color={theme.colors.yellow001} />
        </S.LeftArrow>
        <div>
          {year}년 {month}월
        </div>
        <S.RightArrow onClick={goToNextMonth}>
          <RightArrow width={30} color={theme.colors.yellow001} />
        </S.RightArrow>
      </S.Navigator>
      <S.Content>
        {/* 요일 */}
        {days.map(day => (
          <S.DayColor className={dayClassGenerator(day)} key={day}>
            {day}
          </S.DayColor>
        ))}
        {/* 지난달 */}
        {prevDates.map(date => (
          <S.PrevNextDate key={`${month - 1}-${date}`}>{date}</S.PrevNextDate>
        ))}
        {/* 이번달 */}
        {dates.map(date => (
          <S.Date
            className={
              isToday(date)
                ? 'today'
                : dayClassGenerator(new Date(year, month - 1, date).getDay())
            }
            key={`${month}-${date}`}
          >
            {date}
          </S.Date>
        ))}
        {/* 다음달 */}
        {nextDates.map(date => (
          <S.PrevNextDate key={`${month + 1}-${date}`}>{date}</S.PrevNextDate>
        ))}
      </S.Content>
    </S.Container>
  );
}

export default Calendar;