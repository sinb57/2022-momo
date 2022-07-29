import { useQuery } from 'react-query';

import { getGroupParticipants } from 'apis/request/group';
import { ReactComponent as CrownSVG } from 'assets/crown.svg';
import PersonSVG from 'components/svg/Person';
import { QUERY_KEY } from 'constants/key';
import { DetailData, GroupParticipants } from 'types/data';

import * as S from './index.styled';

interface ParticipantsProps {
  id: DetailData['id'];
  hostName: string;
}

function Participants({ id, hostName }: ParticipantsProps) {
  const { data: participants } = useQuery<GroupParticipants>(
    QUERY_KEY.GROUP_PARTICIPANTS,
    () => getGroupParticipants(id),
    {
      staleTime: Infinity,
    },
  );

  return (
    <S.Container>
      <S.Header>참여자 목록</S.Header>
      <S.Box>
        <S.Wrapper>
          <CrownSVG width={32} />
          <S.HostText>{hostName}</S.HostText>
        </S.Wrapper>
        {participants?.map(participant => (
          <S.Wrapper key={participant.id}>
            <PersonSVG width={32} />
            <S.Text>{participant.name}</S.Text>
          </S.Wrapper>
        ))}
      </S.Box>
    </S.Container>
  );
}

export default Participants;