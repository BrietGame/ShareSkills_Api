import { ValidatorConstraint, ValidatorConstraintInterface, ValidationArguments } from 'class-validator';
import { UsersService } from 'src/users/users.service';

@ValidatorConstraint({ async: true })
export class IsUniqueEmail implements ValidatorConstraintInterface {
  private static usersService: UsersService;

  static setUsersService(usersService: UsersService) {
    IsUniqueEmail.usersService = usersService;
  }

  async validate(email: string, args: ValidationArguments) {
    return IsUniqueEmail.usersService.isEmailUnique(email);
  }

  defaultMessage(args: ValidationArguments) {
    return 'Email $value is already taken!';
  }
}
