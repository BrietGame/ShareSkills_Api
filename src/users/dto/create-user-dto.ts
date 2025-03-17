import { IsNotEmpty, IsString, IsEmail, Validate } from 'class-validator';
import { IsUniqueEmail } from '../../validators/is-unique-email.validator';

export class CreateUserDto {
  @IsNotEmpty()
  @IsString()
  @IsEmail()
  @Validate(IsUniqueEmail)
  email: string;

  @IsNotEmpty()
  @IsString()
  username: string;

  @IsNotEmpty()
  @IsString()
  password: string;
}
