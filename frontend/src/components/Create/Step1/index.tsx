import React, { forwardRef, LegacyRef, memo } from 'react';

import { GROUP_RULE } from 'constants/rule';
import { CreateGroupData } from 'types/data';

import {
  Container,
  ErrorColor,
  Heading,
  Input,
  LabelContainer,
  Label,
} from '../@shared/styled';

interface Step1Props {
  useNameState: () => {
    name: CreateGroupData['name'];
    setName: (name: CreateGroupData['name']) => void;
  };
  pressEnterToNext: (e: React.KeyboardEvent<HTMLInputElement>) => void;
}

function Step1(
  { useNameState, pressEnterToNext }: Step1Props,
  ref: LegacyRef<HTMLDivElement>,
) {
  const { name, setName } = useNameState();
  const changeName = (e: React.ChangeEvent<HTMLInputElement>) => {
    setName(e.target.value);
  };

  return (
    <Container ref={ref}>
      <Heading>
        모임의 <span>이름</span>을 알려주세요. <ErrorColor>*</ErrorColor>
      </Heading>
      <LabelContainer>
        <Label>
          <p>이름</p>
          <p>
            {name.length}/{GROUP_RULE.NAME.MAX_LENGTH}
          </p>
        </Label>
        <Input
          type="text"
          value={name}
          onChange={changeName}
          onKeyPress={pressEnterToNext}
          placeholder="정체를 밝혀라 @_@"
          maxLength={GROUP_RULE.NAME.MAX_LENGTH}
          autoFocus
        />
      </LabelContainer>
    </Container>
  );
}

export default memo(forwardRef(Step1));
