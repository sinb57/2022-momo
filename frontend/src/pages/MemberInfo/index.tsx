import { useEffect, useState } from 'react';

import { useQuery } from 'react-query';

import {
  getUserInfo,
  requestChangeName,
  requestChangePassword,
} from 'apis/request/user';
import { ReactComponent as CompleteSVG } from 'assets/complete.svg';
import { ReactComponent as PencilSVG } from 'assets/pencil.svg';
import cover from 'assets/userInfo_cover.jpg';
import { QUERY_KEY } from 'constants/key';
import { ERROR_MESSAGE, GUIDE_MESSAGE } from 'constants/message';
import useInput from 'hooks/useInput';

import * as S from './index.styled';

function MemberInfo() {
  const { data } = useQuery(QUERY_KEY.USER_INFO, getUserInfo, {
    suspense: true,
  });
  const { value: name, setValue: setName, dangerouslySetValue } = useInput('');
  const { value: password, setValue: setPassword } = useInput('');
  const { value: confirmPassword, setValue: setConfirmPassword } = useInput('');
  const [isEditableName, setIsEditableName] = useState(false);
  const [isEditablePassword, setIsEditablePassword] = useState(false);

  useEffect(() => {
    if (typeof data === 'undefined') return;

    dangerouslySetValue(data.name);
  }, [data, dangerouslySetValue]);

  const changeNameEditable = () => {
    setIsEditableName(true);
  };

  const changePasswordEditable = () => {
    setIsEditablePassword(true);
  };

  const editName = () => {
    requestChangeName(name)
      .then(() => {
        alert(GUIDE_MESSAGE.MEMBER.SUCCESS_NAME_REQUEST);
        setIsEditableName(false);
      })
      .catch(() => {
        alert(ERROR_MESSAGE.MEMBER.FAILURE_NAME_REQUEST);
      });
  };

  const editPassword = () => {
    if (password !== confirmPassword) {
      alert(ERROR_MESSAGE.MEMBER.FAILURE_CONFIRM_PASSWORD);
      return;
    }

    requestChangePassword(password)
      .then(() => {
        alert(GUIDE_MESSAGE.MEMBER.SUCCESS_PASSWORD_REQUEST);
        setIsEditablePassword(false);
      })
      .catch(() => {
        alert(ERROR_MESSAGE.MEMBER.FAILURE_PASSWORD_REQUEST);
      });
  };

  return (
    <S.Container>
      <S.Image src={cover} alt="user info cover" />
      <S.Content>
        <S.Title>회원 정보</S.Title>
        <S.InputContainer>
          <S.Label>
            이메일
            <S.Input type="email" value={data?.email} disabled />
          </S.Label>
          <S.Label>
            닉네임
            <S.InputLineContainer>
              <S.Input
                type="text"
                value={name}
                onChange={setName}
                disabled={!isEditableName}
              />
              {isEditableName ? (
                <S.Button onClick={editName}>
                  <CompleteSVG />
                </S.Button>
              ) : (
                <S.Button onClick={changeNameEditable}>
                  <PencilSVG width={32} />
                </S.Button>
              )}
            </S.InputLineContainer>
          </S.Label>
          <S.Label>
            비밀번호
            <S.InputLineContainer>
              <S.Input
                type="password"
                value={password}
                onChange={setPassword}
                placeholder="********"
                disabled={!isEditablePassword}
              />
              {isEditablePassword ? (
                <S.Button onClick={editPassword}>
                  <CompleteSVG />
                </S.Button>
              ) : (
                <S.Button onClick={changePasswordEditable}>
                  <PencilSVG width={32} />
                </S.Button>
              )}
            </S.InputLineContainer>
          </S.Label>
          {isEditablePassword && (
            <S.Label>
              비밀번호 확인
              <S.Input
                type="password"
                placeholder="비밀번호를 한 번 더 입력해주세요."
                value={confirmPassword}
                onChange={setConfirmPassword}
                required
              />
            </S.Label>
          )}
        </S.InputContainer>
      </S.Content>
    </S.Container>
  );
}

export default MemberInfo;