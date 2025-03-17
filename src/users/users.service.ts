import { Injectable } from "@nestjs/common";
import { InjectModel } from "@nestjs/mongoose";
import { User, UserDocument } from "./schemas/user.schema";
import { Model } from "mongoose";
import { CreateUserDto } from "./dto/create-user-dto";

@Injectable()
export class UsersService {
  constructor(@InjectModel(User.name) private userModel: Model<UserDocument>) {}

  async create(createUserDto: CreateUserDto): Promise<User> {
    const createdCat = new this.userModel(createUserDto);
    return createdCat.save();
  }

  async update(id: string, createUserDto: CreateUserDto): Promise<User | null> {
    return this.userModel.findByIdAndUpdate(id, createUserDto, { new: true });
  }

  async isEmailUnique(email: string): Promise<boolean> {
    const user = await this.userModel.findOne({ email }).exec();
    return !user; // ✅ Retourne `true` si l'email est unique
  }

  // update deleted to true
  async softDelete(id: string): Promise<User | null> {
    return this.userModel.findByIdAndUpdate(id, { deleted: true }, { new: true });
  }

  async findAll(): Promise<User[]> {
    return this.userModel.find().exec();
  }

  async findOne(id: string): Promise<User | null> {
    return this.userModel.findById(id).exec();
  }
}